package com.softhaxi.marves.core.service.message;

import java.util.Map;

import com.softhaxi.marves.core.domain.messaging.Message;
import com.softhaxi.marves.core.repository.messaging.MessageRepository;

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
public class NotificationService<T extends Message> {
    @Autowired
    @Qualifier("oneSignalRestTemplate")
    private RestTemplate restTemplate;

    @Value("${onesignal.app.id}")
    private String appId;

    @Value("${onesignal.notification.endpoint}")
    private String notificationEndPoint;

    @Autowired
    private MessageRepository messageRepo;

    @Async
    public void sendPushNotification(T notification, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(body, headers);
        //body.put("app_id", appId);

        ResponseEntity<?> response = restTemplate.postForEntity(notificationEndPoint, entity, Map.class);

        Map<?, ?> result = (Map<?, ?>) response.getBody();
        notification.setOneSignalId(result.get("id").toString());
        messageRepo.save(notification);
    }
}
