package com.softhaxi.marves.core.domain.employee;

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
import javax.persistence.ManyToOne;
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
@Table(name = "schedules")
@Access(value = AccessType.FIELD)
public class Schedule implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3514886770141576570L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
	@Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;
    
    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime endTime;

    @Column(name = "title", nullable = false, length = 100)
    protected String title;

    @Column(name = "description", nullable = false, length = 100)
    protected String description;

    @Column(name = "location", length = 100)
    protected String location;

    @Column(name = "online_link")
    protected String onlineLink;

    @Column(name = "is_invitation")
    protected boolean isInvitation;

    @Column(name = "inviter")
    protected String inviter;

    @Column(name = "is_finished")
    protected boolean isFinished = false;

    public Schedule() {
    }

    public Schedule(User user, ZonedDateTime startTime, ZonedDateTime endTime, String title, String description, String location, String onlineLink, boolean isInvitation, String inviter, boolean isFinished) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.description = description;
        this.location = location;
        this.onlineLink = onlineLink;
        this.isInvitation = isInvitation;
        this.inviter = inviter;
        this.isFinished = isFinished;
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

    public ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOnlineLink() {
        return this.onlineLink;
    }

    public void setOnlineLink(String onlineLink) {
        this.onlineLink = onlineLink;
    }

    public boolean isInvitation() {
        return this.isInvitation;
    }

    public void setIsInvitation(boolean isInvitation) {
        this.isInvitation = isInvitation;
    }

    public String getInviter() {
        return this.inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Schedule id(UUID id) {
        this.id = id;
        return this;
    }

    public Schedule user(User user) {
        this.user = user;
        return this;
    }

    public Schedule startTime(ZonedDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public Schedule endTime(ZonedDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public Schedule title(String title) {
        this.title = title;
        return this;
    }

    public Schedule description(String description) {
        this.description = description;
        return this;
    }

    public Schedule location(String location) {
        this.location = location;
        return this;
    }

    public Schedule onlineLink(String onlineLink) {
        this.onlineLink = onlineLink;
        return this;
    }

    public Schedule isInvitation(boolean isInvitation) {
        this.isInvitation = isInvitation;
        return this;
    }

    public Schedule inviter(String inviter) {
        this.inviter = inviter;
        return this;
    }

    public Schedule isFinished(boolean isFinished) {
        this.isFinished = isFinished;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Schedule)) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, startTime, endTime, title, description, location, onlineLink, isInvitation, inviter);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", location='" + getLocation() + "'" +
            ", onlineLink='" + getOnlineLink() + "'" +
            ", isInvitation='" + isInvitation() + "'" +
            ", inviter='" + getInviter() + "'" +
            ", isfinished='" + isFinished() + "'" +
            "}";
    }

}
