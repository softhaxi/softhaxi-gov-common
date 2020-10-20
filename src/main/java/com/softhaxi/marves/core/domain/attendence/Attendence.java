package com.softhaxi.marves.core.domain.attendence;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Raja Sihombing
 * @since 1
 */
public abstract class Attendence implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1328697867644739119L;
    protected String id;
    protected String type;
    protected Timestamp dateTime;
    protected String action;
    protected float latitude;
    protected float longitude;
    protected boolean isMockLocation;
    protected String picturePath;


    public Attendence() {
    }

    public Attendence(String id, String type, Timestamp dateTime, String action, float latitude, float longitude, boolean isMockLocation, String picturePath) {
        this.id = id;
        this.type = type;
        this.dateTime = dateTime;
        this.action = action;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isMockLocation = isMockLocation;
        this.picturePath = picturePath;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Attendence id(String id) {
        this.id = id;
        return this;
    }

    public Attendence type(String type) {
        this.type = type;
        return this;
    }

    public Attendence dateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Attendence action(String action) {
        this.action = action;
        return this;
    }

    public Attendence latitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Attendence longitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public Attendence isMockLocation(boolean isMockLocation) {
        this.isMockLocation = isMockLocation;
        return this;
    }

    public Attendence picturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attendence)) {
            return false;
        }
        Attendence attendence = (Attendence) o;
        return Objects.equals(id, attendence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, dateTime, action, latitude, longitude, isMockLocation, picturePath);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
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
