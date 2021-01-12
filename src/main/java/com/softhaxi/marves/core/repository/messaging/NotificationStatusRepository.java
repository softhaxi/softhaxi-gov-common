package com.softhaxi.marves.core.repository.messaging;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.messaging.Notification;
import com.softhaxi.marves.core.domain.messaging.NotificationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationStatusRepository extends JpaRepository<NotificationStatus, UUID> {

    @Query("FROM NotificationStatus WHERE message= ?1 AND user= ?2 AND delivered = false")
    public Optional<NotificationStatus> findOneUndeliveredNotificationByUser(Notification notification, User user);

    @Query("FROM NotificationStatus WHERE message= ?1 AND user= ?2")
	public Optional<NotificationStatus> findOneNotificationStatusByUser(Notification notification, User user); 
}
