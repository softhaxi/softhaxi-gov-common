package com.softhaxi.marves.core.service.message;

import java.time.ZonedDateTime;
import java.util.List;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

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
    
    @Async
    public void sendWelcomeMessage(User user) {
        User admin = userRepo.findByUsername("MCORE.ADMIN").orElse(null);
        if(admin == null) {
            return;
        }

        ChatRoom room = chatRoomRepo.findOnePrivateBy2User(admin, user).orElse(null);
        if(room == null) {
            room = new ChatRoom().name(String.format("Administrator|%s",user.getEmail()));
            chatRoomRepo.save(room);

            chatRoomMemberRepo.saveAll(List.of(
                new ChatRoomMember().chatRoom(room).user(admin),
                new ChatRoomMember().chatRoom(room).user(user)
            ));

            Chat chat = new Chat()
                        .sender(admin)
                        .chatRoom(room)
                        .dateTime(ZonedDateTime.now())
                        .content(String.format("Hi %s, \nSelamat datang di Marves Core", user.getEmail()));
            chatRepo.save(chat);
            chatStatusRepo.save(new ChatStatus(chat, user, false, false));
        }
    }
    
}
