package com.softhaxi.marves.core.repository.chat;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.chatting.Chat;
import com.softhaxi.marves.core.domain.chatting.ChatStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatStatusRepository extends JpaRepository<ChatStatus, UUID> {

    @Query("FROM ChatStatus WHERE message= ?1 AND user= ?2 AND delivered = false")
	public Optional<ChatStatus> findOneUndeliveredChatByUser(Chat chat, User user);

    @Query("FROM ChatStatus WHERE message= ?1 AND user= ?2")
	public Optional<ChatStatus> findOneChatStatusByUser(Chat chat, User user);
    
}
