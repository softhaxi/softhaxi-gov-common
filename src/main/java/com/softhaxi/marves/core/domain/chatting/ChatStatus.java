package com.softhaxi.marves.core.domain.chatting;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.messaging.Message;
import com.softhaxi.marves.core.domain.messaging.MessageStatus;

@Entity
@DiscriminatorValue(value = "CHAT")
public class ChatStatus extends MessageStatus {

    /**
     *
     */
    private static final long serialVersionUID = -6275628873014023971L;
    
    public ChatStatus() {
    }

    public ChatStatus(Message message, User user, boolean delivered, boolean read) {
        super(message, user, delivered, read);
    }
}
