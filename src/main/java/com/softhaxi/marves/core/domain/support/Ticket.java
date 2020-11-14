package com.softhaxi.marves.core.domain.support;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "tickets")
@Access(value = AccessType.FIELD)
public class Ticket extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7393272894581753868L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name= "code", nullable = false, length = 50)
    protected String code;

    @Column(name= "pic", length = 100)
    protected String pic;

    @Column(name= "title")
    protected String title;

    @Column(name= "content")
    protected String content;

    @Column(name= "status")
    protected String status = "open";

    @Column(name= "filename")
    protected String filename;

    @JsonIgnore
    @Column(name= "storage_path")
    protected String storagePath;

    @Column(name= "sla_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime slaDate;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    protected Set<TicketComment> comments;

    public Ticket() {
    }

    public Ticket(User user, String code, String pic, String title, String content, String status, String filename, String storagePath, ZonedDateTime slaDate) {
        this.user = user;
        this.code = code;
        this.pic = pic;
        this.title = title;
        this.content = content;
        this.status = status;
        this.filename = filename;
        this.storagePath = storagePath;
        this.slaDate = slaDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStoragePath() {
        return this.storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public ZonedDateTime getSlaDate() {
        return this.slaDate;
    }

    public void setSlaDate(ZonedDateTime slaDate) {
        this.slaDate = slaDate;
    }

    public ZonedDateTime getDateTime() {
        return getCreatedAt().atZone(ZoneId.systemDefault());
    }

    public String getPhotoUrl() {
        if(getStoragePath() == null)
            return null;
        return String.format("/asset%s", getStoragePath());
    }

    public Set<TicketComment> getComments() {
        return this.comments;
    }

    public void setComments(Set<TicketComment> comments) {
        this.comments = comments;
    }

    public Ticket id(UUID id) {
        this.id = id;
        return this;
    }

    public Ticket user(User user) {
        this.user = user;
        return this;
    }

    public Ticket code(String code) {
        this.code = code;
        return this;
    }

    public Ticket pic(String pic) {
        this.pic = pic;
        return this;
    }

    public Ticket title(String title) {
        this.title = title;
        return this;
    }

    public Ticket content(String content) {
        this.content = content;
        return this;
    }

    public Ticket status(String status) {
        this.status = status;
        return this;
    }

    public Ticket filename(String filename) {
        this.filename = filename;
        return this;
    }

    public Ticket storagePath(String storagePath) {
        this.storagePath = storagePath;
        return this;
    }

    public Ticket slaDate(ZonedDateTime slaDate) {
        this.slaDate = slaDate;
        return this;
    }

    public Ticket comments(Set<TicketComment> comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{'" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", code='" + getCode() + "'" +
            ", pic='" + getPic() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", status='" + getStatus() + "'" +
            ", filename='" + getFilename() + "'" +
            ", storagePath='" + getStoragePath() + "'" +
            ", slaDate='" + getSlaDate() + "'" +
            //", comments='" + getComments() + "'" +
            "}";
    }

}
