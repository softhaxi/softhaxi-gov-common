package com.softhaxi.marves.core.domain.messaging;

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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "messages")
@Inheritance(strategy = InheritanceType.JOINED)
@Access(value = AccessType.FIELD)
public abstract class Message extends Auditable<String> implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = -8940093760474917013L;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "type", nullable = false, length = 50)
    protected String type = "MESSAGE";

    @Column(name= "content")
    protected String content;

    @Column(name = "date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime dateTime;

    @JsonIgnore
    @Column(name= "is_delivered")
    protected boolean delivered = false;

    @Column(name= "is_read")
    protected boolean read = false;

    @JsonIgnore
    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    protected Set<MessageStatus> statuses;

    public Message() {
    }

    public Message(User user, String type, String content, ZonedDateTime dateTime, boolean delivered, boolean read) {
        this.user = user;
        this.type = type;
        this.content = content;
        this.dateTime = dateTime;
        this.delivered = delivered;
        this.read = read;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @JsonIgnore
    public boolean isDelivered() {
        return this.delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Set<MessageStatus> getStatuses() {
        return this.statuses;
    }

    public void setStatuses(Set<MessageStatus> statuses) {
        this.statuses = statuses;
    }

    public Message id(UUID id) {
        this.id = id;
        return this;
    }

    public Message user(User user) {
        this.user = user;
        return this;
    }

    public Message type(String type) {
        this.type = type;
        return this;
    }

    public Message content(String content) {
        this.content = content;
        return this;
    }

    public Message dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Message delivered(boolean delivered) {
        this.delivered = delivered;
        return this;
    }

    public Message read(boolean read) {
        this.read = read;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Message)) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser().getId() + "'" +
            ", type='" + this.type + "'" +
            ", content='" + getContent() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            ", delivered='" + isDelivered() + "'" +
            ", read='" + isRead() + "'" +
            "}";
    }

}
