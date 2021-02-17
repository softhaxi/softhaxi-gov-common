package com.softhaxi.marves.core.domain.logging;

import java.io.Serializable;
import java.time.ZonedDateTime;
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

@Entity
@Table(name = "sessions")
@Access(value = AccessType.FIELD)
public class Session extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "type", nullable = false, length = 50)
    protected String type = "Bearer";

    @Column(name = "access_token", nullable = false)
    protected String accessToken;

    @Column(name = "refresh_token", nullable = true)
    protected String refreshToken;

    @Column(name = "status", nullable = false)
    protected String status = "VALID";

    @Column(name = "last_used", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime lastUsed;

    @Column(name = "onesignal_id")
    protected String oneSignalId;


    public Session() {
    }

    public Session(User user, String type, String accessToken, String refreshToken, String status, ZonedDateTime lastUsed, String oneSignalId) {
        this.user = user;
        this.type = type;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.status = status;
        this.lastUsed = lastUsed;
        this.oneSignalId = oneSignalId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getLastUsed() {
        return this.lastUsed;
    }

    public void setLastUsed(ZonedDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }

    public String getOneSignalId() {
        return this.oneSignalId;
    }

    public void setOneSignalId(String oneSignalId) {
        this.oneSignalId = oneSignalId;
    }

    public Session user(User user) {
        setUser(user);
        return this;
    }

    public Session type(String type) {
        setType(type);
        return this;
    }

    public Session accessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public Session refreshToken(String refreshToken) {
        setRefreshToken(refreshToken);
        return this;
    }

    public Session status(String status) {
        setStatus(status);
        return this;
    }

    public Session lastUsed(ZonedDateTime lastUsed) {
        setLastUsed(lastUsed);
        return this;
    }

    public Session oneSignalId(String oneSignalId) {
        setOneSignalId(oneSignalId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Session)) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, accessToken, refreshToken, status, lastUsed);
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", type='" + getType() + "'" +
            ", accessToken='" + getAccessToken() + "'" +
            ", refreshToken='" + getRefreshToken() + "'" +
            ", status='" + getStatus() + "'" +
            ", lastUsed='" + getLastUsed() + "'" +
            ", oneSignalId='" + getOneSignalId() + "'" +
            "}";
    }

}
