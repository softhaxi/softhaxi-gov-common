package com.softhaxi.marves.core.domain.attendance;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "attendances")
@Inheritance(
    strategy = InheritanceType.JOINED
)
@Access(value = AccessType.FIELD)
public abstract class Attendance extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1328697867644739119L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "type", nullable = false, length = 50)
    protected String type = "ATTENDENCE";

    @Column(name = "date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    protected ZonedDateTime dateTime;

    @Column(name = "action", length = 10)
    protected String action;

    @Column(name = "latitude")
    protected double latitude;

    @Column(name = "longitude")
    protected double longitude;

    @Column(name = "is_mock_location")
    protected boolean isMockLocation;

    @JsonIgnore
    @Column(name = "picture_path")
    protected String picturePath;


    public Attendance() {
    }

    public Attendance(User user, String type, ZonedDateTime dateTime, String action, double latitude, double longitude, boolean isMockLocation, String picturePath) {
        this.user = user;
        this.type = type;
        this.dateTime = dateTime;
        this.action = action;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isMockLocation = isMockLocation;
        this.picturePath = picturePath;
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

    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isMockLocation() {
        return this.isMockLocation;
    }

    public void setIsMockLocation(boolean isMockLocation) {
        this.isMockLocation = isMockLocation;
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPhotoUrl() {
        if(getPicturePath() == null)
            return null;
        return String.format("/asset%s", getPicturePath());
    }

    public Attendance id(UUID id) {
        this.id = id;
        return this;
    }

    public Attendance user(User user) {
        this.user = user;
        return this;
    }

    public Attendance type(String type) {
        this.type = type;
        return this;
    }

    public Attendance dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Attendance action(String action) {
        this.action = action;
        return this;
    }

    public Attendance latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Attendance longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Attendance isMockLocation(boolean isMockLocation) {
        this.isMockLocation = isMockLocation;
        return this;
    }

    public Attendance picturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attendance)) {
            return false;
        }
        Attendance attendence = (Attendance) o;
        return Objects.equals(id, attendence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.getId(), type, dateTime, action, latitude, longitude, isMockLocation, picturePath);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser().getId() + "'" +
            ", type='" + getType() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            ", action='" + getAction() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", isMockLocation='" + isMockLocation() + "'" +
            ", picturePath='" + getPicturePath() + "'" +
            "}";
    }

}
