package com.softhaxi.marves.core.domain.messaging;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.account.User;

@Entity
@Table(name = "notifications")
@Access(value = AccessType.FIELD)
public class Notification extends Message {

    /**
     *
     */
    private static final long serialVersionUID = -8159333838055392737L;

    @JsonIgnore
    @Column(name = "level", nullable = false)
    protected String level = "GENERAL";

    @JsonIgnore
    @Column(name = "assignee", nullable = false)
    protected String assignee = "ALL";

    @Column(name = "deep_link", length = 200)
    protected String deepLink;

    @JsonIgnore
    @Column(name = "uri", length = 200)
    protected String uri;

    @Column(name = "reference_id", length = 50)
    protected String referenceId;

    public Notification() {
        this.type = "NOTIFICATION";
    }

    public Notification(User user, String content, ZonedDateTime dateTime, boolean delivered, boolean read, 
            String level, String assignee, String deepLink, String uri, String referenceId) {
        super(user, "NOTIFICATION", content, dateTime, delivered, read);
        this.level = level;
        this.assignee = assignee;
        this.deepLink = deepLink;
        this.uri = uri;
        this.referenceId = referenceId;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
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

    public Notification level(String level) {
        this.level = level;
        return this;
    }

    public Notification assignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public Notification deepLink(String deepLink) {
        this.deepLink = deepLink;
        return this;
    }

    public Notification uri(String uri) {
        this.uri = uri;
        return this;
    }

    public Notification referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Notification)) {
            return false;
        }
        Notification notification = (Notification) o;
        return Objects.equals(id, notification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" + " level='" + getId() + "'" + ", level='" + getLevel() + "'" + ", assignee='" + getAssignee() + "'"
                + ", deepLink='" + getDeepLink() + "'" + ", uri='" + getUri() + "'" + ", referenceId='"
                + getReferenceId() + "'" + "}";
    }
}
