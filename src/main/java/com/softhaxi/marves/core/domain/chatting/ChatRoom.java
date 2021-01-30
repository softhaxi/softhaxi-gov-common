package com.softhaxi.marves.core.domain.chatting;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;

@Entity
@Table(name = "chatrooms")
@Access(value = AccessType.FIELD)
public class ChatRoom extends Auditable<String> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7914593559400932853L;
    @Column(name = "code", length = 50)
    protected String code;

    @Column(name = "name", length = 100)
    protected String name;

    @Column(name = "level", nullable = false, length = 20)
    protected String level = "O2O"; // O2O, O2G

    @JsonIgnore
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    protected Set<ChatRoomMember> members;

    @JsonIgnore
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    @OrderBy(value = "dateTime ASC")
    protected Set<Chat> chats;

    @Transient
    protected String recipient;

    @Transient
    protected Chat latestChat;

    public ChatRoom() {
    }

    public ChatRoom(String code, String name, String level) {
        this.code = code;
        this.name = name;
        this.level = level;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set<ChatRoomMember> getMembers() {
        return this.members;
    }

    public void setMembers(Set<ChatRoomMember> members) {
        this.members = members;
    }

    public Set<Chat> getChats() {
        return this.chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Chat getLatestChat() {
        return latestChat;
    }

    public void setLatestChat(Chat latestChat) {
        this.latestChat = latestChat;
    }

    public ChatRoom id(UUID id) {
        this.id = id;
        return this;
    }

    public ChatRoom code(String code) {
        this.code = code;
        return this;
    }

    public ChatRoom name(String name) {
        this.name = name;
        return this;
    }

    public ChatRoom level(String level) {
        this.level = level;
        return this;
    }

    public ChatRoom members(Set<ChatRoomMember> members) {
        this.members = members;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChatRoom)) {
            return false;
        }
        ChatRoom chatRoom = (ChatRoom) o;
        return Objects.equals(id, chatRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", level='" + getLevel() + "'" +
            "}";
    }
    
}
