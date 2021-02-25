package com.softhaxi.marves.core.model.support;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public class Location implements Serializable {
    private UUID id;
    private UUID userId;
    private String email;
    private String fullName;
    private String divisionName;
    private String profilePicture;
    private double latitude;
    private double longitude;
    private ZonedDateTime dateTime;


    public Location() {
    }

    public Location(UUID id, UUID userId, String email, String fullName, String divisionName, String profilePicture, double latitude, double longitude, ZonedDateTime dateTime) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.divisionName = divisionName;
        this.profilePicture = profilePicture;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDivisionName() {
        return this.divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTimeDisplay() {
        LocalDate date = this.dateTime.toLocalDate();
        if(date.equals(LocalDate.now())) {
            return dateTime.format(DateTimeFormatter.ofPattern("HH:mm").withLocale(new Locale("id", "ID")));
        }
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yy HH:mm").withLocale(new Locale("id", "ID")));
    }

    public Location id(UUID id) {
        setId(id);
        return this;
    }

    public Location userId(UUID userId) {
        setUserId(userId);
        return this;
    }

    public Location email(String email) {
        setEmail(email);
        return this;
    }

    public Location fullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    public Location divisionName(String divisionName) {
        setDivisionName(divisionName);
        return this;
    }

    public Location profilePicture(String profilePicture) {
        setProfilePicture(profilePicture);
        return this;
    }

    public Location latitude(double latitude) {
        setLatitude(latitude);
        return this;
    }

    public Location longitude(double longitude) {
        setLongitude(longitude);
        return this;
    }

    public Location dateTime(ZonedDateTime dateTime) {
        setDateTime(dateTime);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", email='" + getEmail() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", divisionName='" + getDivisionName() + "'" +
            ", profilePicture='" + getProfilePicture() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            "}";
    }

}
