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
import javax.validation.constraints.NotBlank;

import com.softhaxi.marves.core.domain.account.User;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
	@Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    protected User user;

    @NotBlank
    @Column(name="date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    protected ZonedDateTime dateTime;

    @NotBlank
    @Column(name="latitude")
    protected double latitude = 0.0;

    @NotBlank
    @Column(name="longitude")
    protected double longitude = 0.0;

    @NotBlank
    @Column(name="is_mock_location")
    protected boolean isMockLocation = false;


    public LocationLog() {

    }

    public LocationLog(User user, ZonedDateTime dateTime, double latitude, double longitude, boolean isMockLocation) {
        this.user = user;
        this.dateTime = dateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isMockLocation = isMockLocation;
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

    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
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

    public LocationLog id(UUID id) {
        this.id = id;
        return this;
    }

    public LocationLog user(User user) {
        this.user = user;
        return this;
    }

    public LocationLog dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public LocationLog latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationLog longitude(double longitude) {
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
