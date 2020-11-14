package com.softhaxi.marves.core.domain.messaging;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.softhaxi.marves.core.domain.account.User;

@Entity
@DiscriminatorValue(value = "NOTIFICATION")
public class NotificationStatus extends MessageStatus {

    /**
     *
     */
    private static final long serialVersionUID = -3186337897173589509L;

    public NotificationStatus() {
    }

    public NotificationStatus(Message message, User user, boolean delivered, boolean read) {
        super(message, user, delivered, read);
    }
}
