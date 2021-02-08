package com.softhaxi.marves.core.service.message;

import java.util.Collection;
import java.util.Map;

import com.softhaxi.marves.core.domain.messaging.Message;
import com.softhaxi.marves.core.domain.messaging.MessageStatus;
import com.softhaxi.marves.core.repository.messaging.MessageRepository;
import com.softhaxi.marves.core.repository.messaging.MessageStatusRepository;

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
public class MessageService {
    @Autowired
    @Qualifier("oneSignalRestTemplate")
    private RestTemplate restTemplate;

    @Value("${onesignal.app.id}")
    private String appId;

    @Value("${onesignal.notification.endpoint}")
    private String notificationEndPoint;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private MessageStatusRepository statusRepo;

    @Async
    public void sendPushNotification(Message message, Collection<? extends MessageStatus> statuses, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(body, headers);
        body.put("app_id", appId);

        ResponseEntity<?> response = restTemplate.postForEntity(notificationEndPoint, entity, Map.class);

        Map<?, ?> result = (Map<?, ?>) response.getBody();
        message.setOneSignalId(result.get("id").toString());
        if(message.getOneSignalId() != null) {
            if(statuses != null && !statuses.isEmpty()) {
                statuses.forEach((status) -> {
                    status.setDelivered(true);
                });
            }
        }
        messageRepo.save(message);
        statusRepo.saveAll(statuses);
    }
}
