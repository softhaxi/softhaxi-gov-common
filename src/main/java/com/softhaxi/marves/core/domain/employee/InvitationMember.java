package com.softhaxi.marves.core.domain.employee;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "invitation_members")
@Access(value = AccessType.FIELD)
public class InvitationMember extends Auditable<String> implements Serializable  {

    /**
     *
     */
    private static final long serialVersionUID = -7846104392842933774L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invitation_id")
    protected Invitation invitation;

    @JsonIgnore
    @JoinColumn(name = "on_behalf_of")
    protected InvitationMember onBehalfOf;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "response", nullable = true, length = 50)
    protected String response = "NA"; // ACCEPT, REJECT, TENTATIVE

    @Column(name = "status", nullable = true, length = 50)
    protected String status; // ATTENDED, NOT ATTENDED

    @Column(name = "is_organizer")
    protected boolean organizer;

    @JsonIgnore
    @Column(name = "is_deleted")
    protected boolean deleted;

    public InvitationMember() {
    }

    public InvitationMember(Invitation invitation, InvitationMember onBehalfOf, User user, String response, String status, boolean organizer) {
        this.invitation = invitation;
        this.onBehalfOf = onBehalfOf;
        this.user = user;
        this.response = response;
        this.status = status;
        this.organizer = organizer;
    }

    public Invitation getInvitation() {
        return this.invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    public InvitationMember getOnBehalfOf() {
        return this.onBehalfOf;
    }

    public void setOnBehalfOf(InvitationMember onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOrganizer() {
        return this.organizer;
    }

    public boolean getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(boolean organizer) {
        this.organizer = organizer;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public InvitationMember invitation(Invitation invitation) {
        setInvitation(invitation);
        return this;
    }

    public InvitationMember onBehalfOf(InvitationMember onBehalfOf) {
        setOnBehalfOf(onBehalfOf);
        return this;
    }

    public InvitationMember user(User user) {
        setUser(user);
        return this;
    }

    public InvitationMember response(String response) {
        setResponse(response);
        return this;
    }

    public InvitationMember status(String status) {
        setStatus(status);
        return this;
    }

    public InvitationMember organizer(boolean organizer) {
        setOrganizer(organizer);
        return this;
    }

    public InvitationMember deleted(boolean deleted) {
        setDeleted(deleted);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InvitationMember)) {
            return false;
        }
        InvitationMember invitationMember = (InvitationMember) o;
        return Objects.equals(id, invitationMember.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invitation.getId(), user.getId(), response, status, organizer);
    }

    @Override
    public String toString() {
        return "{" +
            " invitation='" + getInvitation() + "'" +
            ", onBehalfOf='" + getOnBehalfOf() + "'" +
            ", user='" + getUser() + "'" +
            ", response='" + getResponse() + "'" +
            ", status='" + getStatus() + "'" +
            ", organizer='" + isOrganizer() + "'" +
            "}";
    }

}
