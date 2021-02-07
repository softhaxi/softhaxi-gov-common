package com.softhaxi.marves.core.repository.messaging;

import java.util.UUID;

import com.softhaxi.marves.core.domain.messaging.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    
}
