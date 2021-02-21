package com.softhaxi.marves.core.domain.chatting;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.messaging.Message;

import org.springframework.http.MediaType;

@Entity
@Table(name = "chats")
@Access(value = AccessType.FIELD)
public class Chat extends Message {

    /**
     *
     */
    private static final long serialVersionUID = 1801040393752542738L;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    protected ChatRoom chatRoom;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    protected User sender;

    @Column(name = "content_type", nullable = true)
    protected String contentType = MediaType.TEXT_PLAIN_VALUE;

    @Transient
    protected boolean myself = false;

    public Chat() {
        this.type = "CHAT";
    }

    public Chat(User user, String content, ZonedDateTime dateTime, boolean delivered, boolean read,
        ChatRoom chatRoom, User sender) {
        super(user, "CHAT", content, dateTime, delivered, read);
        this.chatRoom = chatRoom;
        this.sender = sender;
    }

    public ChatRoom getChatRoom() {
        return this.chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public UUID getChatRoomId() {
        return this.chatRoom.getId();
    }

    public String getSenderName() {
        return this.sender.getEmail();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isMyself() {
        return this.myself;
    }

    public void setMyself(boolean myself) {
        this.myself = myself;
    }

    public String getDateTimeDisplay() {
        LocalDate date = this.dateTime.toLocalDate();
        if(date.equals(LocalDate.now())) {
            return dateTime.format(DateTimeFormatter.ofPattern("HH:mm").withLocale(new Locale("id", "ID")));
        }
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yy HH:mm").withLocale(new Locale("id", "ID")));
    }

    public Chat chatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        return this;
    }

    public Chat sender(User sender) {
        this.sender = sender;
        return this;
    }

	@Override
	public Chat content(String content) {
        this.content = content;
		return this;
	}

	@Override
	public Chat dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
		return this;
    }
    
	public Chat contentType(String contentType) {
        this.contentType = contentType;
		return this;
	}

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Chat)) {
            return false;
        }
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", chatRoom='" + getChatRoom().getId() + "'" +
            ", sender='" + getSender() + "'" +
            "}";
    }

}
