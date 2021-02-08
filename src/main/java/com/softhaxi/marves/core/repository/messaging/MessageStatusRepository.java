package com.softhaxi.marves.core.repository.messaging;

import java.util.UUID;

import com.softhaxi.marves.core.domain.messaging.MessageStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageStatusRepository extends JpaRepository<MessageStatus, UUID> {
    
}
