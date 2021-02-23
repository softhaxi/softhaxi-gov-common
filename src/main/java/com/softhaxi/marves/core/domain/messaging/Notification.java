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
import com.softhaxi.marves.core.enums.NotificationCategory;

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
    protected String level = "PUBLIC";

    @JsonIgnore
    @Column(name = "assignee", nullable = false)
    protected String assignee = "ALL"; // ALL, DIVISION

    @JsonIgnore
    @Column(name = "category", nullable = false)
    protected String category = "GENERAL"; // GENERAL, WEDDING, CONDOLENCE, EMERGENCY, OTHERS

    @Column(name = "deep_link", length = 200)
    protected String deepLink;

    @JsonIgnore
    @Column(name = "uri", length = 200)
    protected String uri;

    @Column(name = "reference_id", length = 50)
    protected String referenceId;

    @Column(name = "assignee_name")
    protected String assigneeName;

    @JsonIgnore
    @Column(name = "source", length = 50)
    protected String source = "SYSTEM"; // WEB, SYSTEM

    public Notification() {
        this.type = "NOTIFICATION";
    }

    public Notification(User user, String content, ZonedDateTime dateTime, boolean delivered, boolean read, 
            String level, String assignee, String category, String deepLink, String uri, String referenceId, String assigneeName, String source) {
        super(user, "NOTIFICATION", content, dateTime, delivered, read);
        this.level = level;
        this.assignee = assignee;
        this.category = category;
        this.deepLink = deepLink;
        this.uri = uri;
        this.referenceId = referenceId;
        this.assigneeName = assigneeName;
        this.source = source;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getAssigneeName() {
        return this.assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategoryDisplay() {
        try {
            NotificationCategory category = NotificationCategory.valueOf(this.category.toUpperCase());
            return category.getValue();
        } catch(Exception ex) {
            return null;
        }
    }

    public Notification level(String level) {
        this.level = level;
        return this;
    }

    public Notification assignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public Notification category(String category) {
        this.category = category;
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

    public Notification assigneeName(String assigneeName) {
        setAssigneeName(assigneeName);
        return this;
    }

    public Notification source(String source) {
        setSource(source);
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
                + ", category='" + getCategory() + "'" + ", deepLink='" + getDeepLink() + "'" + ", uri='" + getUri() + "'" + ", referenceId='"
                + getReferenceId() + "'" + "}";
    }
}
