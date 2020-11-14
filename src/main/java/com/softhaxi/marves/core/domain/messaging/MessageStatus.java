package com.softhaxi.marves.core.domain.messaging;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.account.User;

@Entity
@Table(name = "message_statuses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 100)
@DiscriminatorValue(value = "MESSAGE")
@Access(value = AccessType.FIELD)
public abstract class MessageStatus extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1305397294691077878L;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    protected Message message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;
    
    @Column(name= "is_delivered")
    protected boolean delivered = false;

    @Column(name= "is_read")
    protected boolean read = false;

    public MessageStatus() {
    }

    public MessageStatus(Message message, User user, boolean delivered, boolean read) {
        this.message = message;
        this.user = user;
        this.delivered = delivered;
        this.read = read;
    }

    public Message getMessage() {
        return this.message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public MessageStatus message(Message message) {
        this.message = message;
        return this;
    }

    public MessageStatus user(User user) {
        this.user = user;
        return this;
    }

    public MessageStatus delivered(boolean delivered) {
        this.delivered = delivered;
        return this;
    }

    public MessageStatus read(boolean read) {
        this.read = read;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MessageStatus)) {
            return false;
        }
        MessageStatus messageStatus = (MessageStatus) o;
        return Objects.equals(id, messageStatus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +  
            " id='" + getId() + "'" +
            ", message='" + getMessage().getId() + "'" +
            ", user='" + getUser().getId() + "'" +
            ", isDelivered='" + isDelivered() + "'" +
            ", isRead='" + isRead() + "'" +
            "}";
    }

}
