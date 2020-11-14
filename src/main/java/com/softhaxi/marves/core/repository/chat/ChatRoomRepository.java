package com.softhaxi.marves.core.repository.chat;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.chatting.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {

    @Query("SELECT DISTINCT a FROM ChatRoom a " +
        "JOIN ChatRoomMember b ON b.chatRoom.id=a.id AND b.user = ?1")
    public Collection<ChatRoom> findAllByUser(User user);

    @Query("SELECT a FROM ChatRoom a " +
        "JOIN ChatRoomMember b ON b.chatRoom.id=a.id AND b.user = ?1 " +
        "JOIN ChatRoomMember c ON c.chatRoom.id=b.chatRoom.id AND c.user = ?2 " +
        "WHERE a.level='O2O'")
    public Optional<ChatRoom> findOnePrivateBy2User(User user1, User user2);

    @Query("SELECT a FROM ChatRoom a " +
        "JOIN ChatRoomMember b ON b.chatRoom.id=a.id AND b.user = ?2 " +
        "WHERE a.id = ?1")
    public Optional<ChatRoom> findOneByIdAndUser(UUID id, User user);
}
