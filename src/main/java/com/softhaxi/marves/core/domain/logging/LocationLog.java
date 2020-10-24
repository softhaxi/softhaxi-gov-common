package com.softhaxi.marves.core.domain.logging;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */

@Entity
@Table(name = "location_logs")
@Access(value = AccessType.FIELD)
public class LocationLog implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1611171951226492406L;

    @Id
    @NotBlank
	@Column(name = "id")
    protected String id;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    protected User user;

    @NotBlank
    @Column(name="date_time")
    protected Timestamp dateTime;

    @NotBlank
    @Column(name="latitude")
    protected float latitude;

    @NotBlank
    @Column(name="longitude")
    protected float longitude;

    @NotBlank
    @Column(name="is_mock_location")
    protected boolean isMockLocation;


    public LocationLog() {
        isMockLocation = false;
    }

    public LocationLog(String id, User user, Timestamp dateTime, float latitude, float longitude, boolean isMockLocation) {
        this.id = id;
        this.user = user;
        this.dateTime = dateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isMockLocation = isMockLocation;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isMockLocation() {
        return this.isMockLocation;
    }

    public void setIsMockLocation(boolean isMockLocation) {
        this.isMockLocation = isMockLocation;
    }

    public LocationLog id(String id) {
        this.id = id;
        return this;
    }

    public LocationLog user(User user) {
        this.user = user;
        return this;
    }

    public LocationLog dateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public LocationLog latitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationLog longitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationLog isMockLocation(boolean isMockLocation) {
        this.isMockLocation = isMockLocation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LocationLog)) {
            return false;
        }
        LocationLog locationLog = (LocationLog) o;
        return Objects.equals(id, locationLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.getId(), dateTime, latitude, longitude, isMockLocation);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser().getId() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", isMockLocation='" + isMockLocation() + "'" +
            "}";
    }

}
