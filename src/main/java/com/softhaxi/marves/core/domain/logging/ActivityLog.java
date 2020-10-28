package com.softhaxi.marves.core.domain.logging;

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
@Table(name = "activity_logs")
@Access(value = AccessType.FIELD)
public class ActivityLog implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
	@Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    protected User user;

    @Column(name = "deep_link", nullable = false, length = 200)
    protected String deepLink;

    @Column(name = "uri", nullable = false, length = 200)
    protected String uri;

    @Column(name = "reference_id", length = 50)
    protected String referenceId;

    @Column(name = "action_name", length = 20)
    protected String actionName;

    // @Column(name="action_date", nullable = false)
    // protected LocalDate actionDate;

    @Column(name="action_time", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    protected ZonedDateTime actionTime;

    @Column(name="description")
    protected String description;

    public ActivityLog() {
    }

    public ActivityLog(User user, String deepLink, String uri, String referenceId, String actionName, ZonedDateTime actionTime, String description) {
        this.user = user;
        this.deepLink = deepLink;
        this.uri = uri;
        this.referenceId = referenceId;
        this.actionName = actionName;
        // this.actionDate = actionDate;
        this.actionTime = actionTime;
        this.description = description;
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

    public String getDeepLink() {
        return this.deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    // public LocalDate getActionDate() {
    //     return this.actionDate;
    // }

    // public void setActionDate(LocalDate actionDate) {
    //     this.actionDate = actionDate;
    // }

    public ZonedDateTime getActionTime() {
        return this.actionTime;
    }

    public void setActionTime(ZonedDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityLog id(UUID id) {
        this.id = id;
        return this;
    }

    public ActivityLog user(User user) {
        this.user = user;
        return this;
    }

    public ActivityLog deepLink(String deepLink) {
        this.deepLink = deepLink;
        return this;
    }

    public ActivityLog uri(String uri) {
        this.uri = uri;
        return this;
    }

    public ActivityLog referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public ActivityLog actionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    // public ActivityLog actionDate(LocalDate actionDate) {
    //     this.actionDate = actionDate;
    //     return this;
    // }

    public ActivityLog actionTime(ZonedDateTime actionTime) {
        this.actionTime = actionTime;
        return this;
    }

    public ActivityLog description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ActivityLog)) {
            return false;
        }
        ActivityLog activityLog = (ActivityLog) o;
        return Objects.equals(id, activityLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, deepLink, uri, referenceId, actionName, actionTime, description);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", deepLink='" + getDeepLink() + "'" +
            ", uri='" + getUri() + "'" +
            ", referenceId='" + getReferenceId() + "'" +
            ", actionName='" + getActionName() + "'" +
            ", actionTime='" + getActionTime() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}
