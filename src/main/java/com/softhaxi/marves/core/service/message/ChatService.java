package com.softhaxi.marves.core.service.message;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.chatting.Chat;
import com.softhaxi.marves.core.domain.chatting.ChatRoom;
import com.softhaxi.marves.core.domain.chatting.ChatStatus;
import com.softhaxi.marves.core.domain.chatting.ChatRoomMember;
import com.softhaxi.marves.core.repository.account.UserRepository;
import com.softhaxi.marves.core.repository.chat.ChatRepository;
import com.softhaxi.marves.core.repository.chat.ChatRoomRepository;
import com.softhaxi.marves.core.repository.chat.ChatStatusRepository;
import com.softhaxi.marves.core.repository.chat.ChatRoomMemberRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Autowired 
    private UserRepository userRepo;

    @Autowired
    private ChatRoomRepository chatRoomRepo;

    @Autowired
    private ChatRoomMemberRepository chatRoomMemberRepo;

    @Autowired
    private ChatRepository chatRepo;

    @Autowired
    private ChatStatusRepository chatStatusRepo;

    @Autowired
    @Qualifier("oneSignalRestTemplate")
    private RestTemplate restTemplate;

    @Value("${onesignal.app.id}")
    private String appId;

    @Value("${onesignal.notification.endpoint}")
    private String notificationEndPoint;
    
    @Async
    public void sendWelcomeMessage(User user) {
        User admin = userRepo.findByUsername("MCORE.ADMIN").orElse(null);
        if(admin == null) {
            return;
        }

        Chat chat = null;
        ChatRoom room = chatRoomRepo.findOnePrivateBy2User(admin, user).orElse(null);
        if(room == null) {
            room = new ChatRoom().name(String.format("Administrator|%s",user.getEmail()));
            chatRoomRepo.save(room);

            chatRoomMemberRepo.saveAll(List.of(
                new ChatRoomMember().chatRoom(room).user(admin),
                new ChatRoomMember().chatRoom(room).user(user)
            ));

            chat = new Chat()
                        .sender(admin)
                        .chatRoom(room)
                        .dateTime(ZonedDateTime.now())
                        .content(String.format("Hi %s, \nSelamat datang di Marves Core", user.getEmail()));
        }

        if(chat != null) {
            if(user.getOneSignalId() != null && !user.getOneSignalId().isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json; charset=utf-8");
                Map<?, ?> body = Map.of(
                    "app_id", appId,
                    "headings", Map.of("en", "Administrator"),
                    "contents", Map.of("en", chat.getContent()),
                    "data", Map.of("deepLink", "core://marves.dev/chat", 
                        "view", "detail", 
                        "refId", chat.getChatRoom().getId().toString()),
                    "include_player_ids", Arrays.asList(user.getOneSignalId()),
                    "small_icon", "ic_stat_marves",
                    "android_channel_id", "066ee9a7-090b-4a42-b084-0dcbbeb7f158",
                    "android_accent_color", "FF0983E7",
                    "ttl", "180");
                HttpEntity<Map<?, ?>> entity = new HttpEntity<>(body, headers);
                ResponseEntity<?> response = restTemplate.postForEntity(notificationEndPoint, entity, Map.class);

                Map<?, ?> result = (Map<?, ?>) response.getBody();
                logger.info("[sendWelcomeMessage] Result....{}", result);
                chat.setOneSignalId(result.get("id").toString());
            }
        }

        chatRepo.save(chat);
        if(chat.getOneSignalId() != null && !chat.getOneSignalId().isEmpty())
            chatStatusRepo.save(new ChatStatus(chat, user, true, false));
        else
        chatStatusRepo.save(new ChatStatus(chat, user, false, false));
    }
    
}
