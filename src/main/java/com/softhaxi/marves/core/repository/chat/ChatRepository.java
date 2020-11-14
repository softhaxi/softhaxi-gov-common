package com.softhaxi.marves.core.repository.chat;

import java.util.Collection;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.chatting.Chat;
import com.softhaxi.marves.core.domain.chatting.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    @Query("SELECT A FROM Chat A " + 
        "JOIN ChatRoom B ON B.id=A.chatRoom.id " +
        "JOIN ChatRoomMember C ON C.chatRoom.id=B.id AND C.user = ?1 " + 
        "LEFT JOIN ChatStatus D ON D.message.id=A.id " +
        "WHERE A.sender <> ?1 AND (D.user IS NULL OR D.user = ?1) AND (D.delivered IS NULL OR D.delivered = false) " +
        "ORDER BY A.dateTime ASC")
    public Collection<Chat> findAllUndeliveredByUser(User user);
    
    @Query("FROM Chat WHERE chatRoom = ?1 ORDER BY dateTime ASC")
	public Collection<Chat> findAllByChatRoom(ChatRoom chatRoom);
}
