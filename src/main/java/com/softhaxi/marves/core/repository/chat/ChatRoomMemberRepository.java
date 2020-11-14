package com.softhaxi.marves.core.repository.chat;

import java.util.UUID;

import com.softhaxi.marves.core.domain.chatting.ChatRoomMember;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, UUID> {
    
}
