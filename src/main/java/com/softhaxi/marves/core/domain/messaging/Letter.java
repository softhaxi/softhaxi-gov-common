package com.softhaxi.marves.core.domain.messaging;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.account.User;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "letters")
@Access(value = AccessType.FIELD)
public class Letter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6439700040050586697L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
	@Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @Column(name = "app_source", length = 100)
    protected String appSource;

    @Column(name = "reference_id", length = 50)
    protected String referenceId;
    
    @Column(name = "sender", length = 100)
    protected String sender;
    
    @Column(name = "title", length = 100)
    protected String title;

    @Column(name="date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime dateTime;

    @Column(name="received_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime receivedAt;

    protected String importance;

    @Column(name = "is_read")
    protected boolean isRead = false;


    public Letter() {
    }

    public Letter(User user, String appSource, String referenceId, String sender, String title, ZonedDateTime dateTime, ZonedDateTime receivedAt, String importance, boolean isRead) {
        this.user = user;
        this.appSource = appSource;
        this.referenceId = referenceId;
        this.sender = sender;
        this.title = title;
        this.dateTime = dateTime;
        this.receivedAt = receivedAt;
        this.importance = importance;
        this.isRead = isRead;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppSource() {
        return this.appSource;
    }

    public void setAppSource(String appSource) {
        this.appSource = appSource;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ZonedDateTime getReceivedAt() {
        return this.receivedAt;
    }

    public void setReceivedAt(ZonedDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getImportance() {
        return this.importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public boolean isIsRead() {
        return this.isRead;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Letter id(UUID id) {
        this.id = id;
        return this;
    }

    public Letter user(User user) {
        this.user = user;
        return this;
    }

    public Letter appSource(String appSource) {
        this.appSource = appSource;
        return this;
    }

    public Letter referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public Letter sender(String sender) {
        this.sender = sender;
        return this;
    }

    public Letter title(String title) {
        this.title = title;
        return this;
    }

    public Letter dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Letter receivedAt(ZonedDateTime receivedAt) {
        this.receivedAt = receivedAt;
        return this;
    }

    public Letter importance(String importance) {
        this.importance = importance;
        return this;
    }

    public Letter isRead(boolean isRead) {
        this.isRead = isRead;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Letter)) {
            return false;
        }
        Letter letter = (Letter) o;
        return Objects.equals(id, letter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, appSource, referenceId, sender, title, dateTime, receivedAt, importance, isRead);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser().getId() + "'" +
            ", appSource='" + getAppSource() + "'" +
            ", referenceId='" + getReferenceId() + "'" +
            ", sender='" + getSender() + "'" +
            ", title='" + getTitle() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            ", receiveAt='" + getReceivedAt() + "'" +
            ", importance='" + getImportance() + "'" +
            ", isRead='" + isIsRead() + "'" +
            "}";
    }

}
