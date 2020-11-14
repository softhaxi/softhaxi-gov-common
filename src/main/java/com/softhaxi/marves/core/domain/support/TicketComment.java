package com.softhaxi.marves.core.domain.support;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.messaging.Message;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "ticket_comments")
@Access(value = AccessType.FIELD)
public class TicketComment extends Message {

    /**
     *
     */
    private static final long serialVersionUID = -1942863325550594359L;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    protected Ticket ticket;

    @Column(name = "filename")
    protected String filename;

    @Column(name = "storage_path")
    protected String storagePath;

    public TicketComment() {
        this.type = "TICKETCOMMENT";
    }

    public TicketComment(User user, String content, ZonedDateTime dateTime, boolean isDelivered, boolean isRead, Ticket ticket, String filename, String storagePath) {
        super(user, "TICKETCOMMENT", content, dateTime, isDelivered, isRead);
        this.ticket = ticket;
        this.filename = filename;
        this.storagePath = storagePath;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

	@Override
	public TicketComment user(User user) {
        setUser(user);
		return this;
    }
    
	@Override
	public TicketComment content(String content) {
        setContent(content);
		return this;
	}

    public TicketComment ticket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    public TicketComment filename(String filename) {
        this.filename = filename;
        return this;
    }

    public TicketComment storagePath(String storagePath) {
        this.storagePath = storagePath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TicketComment)) {
            return false;
        }
        TicketComment ticketComment = (TicketComment) o;
        return Objects.equals(id, ticketComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " ticket='" + getTicket().getId() + "'" +
            ", filename='" + getFilename() + "'" +
            ", storagePath='" + getStoragePath() + "'" +
            "}";
    }

}
