package com.softhaxi.marves.core.domain.attendence;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.softhaxi.marves.core.domain.account.User;
/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "meeting_attendences")
@Access(value = AccessType.FIELD)
public class MeetingAttendence extends Attendence {

    /**
     *
     */
    private static final long serialVersionUID = 449769246567348831L;

    @Column(name = "code", length = 100)
    protected String code;

    @Column(name = "reference_id", length = 100)
    protected String referenceId;

    @Column(name = "location", length = 100)
    protected String location;

    @Column(name = "organizer", length = 100)
    protected String organizer;

    @Column(name = "title", length = 100)
    protected String title;

    @Column(name = "description")
    protected String description;

    @Column(name = "start_time", length = 16)
    protected String startTime;

    @Column(name = "end_time", length = 16)
    protected String endTime;


    public MeetingAttendence() {
        setType("MEETING");
    }

    public MeetingAttendence(String id, User user, ZonedDateTime dateTime, String action, double latitude, double longitude, boolean isMockLocation, String picturePath, 
    String code, String referenceId, String location, String organizer, String title, String description, String startTime, String endTime) {
        super(user, "MEETING", dateTime, action, latitude, longitude, isMockLocation, picturePath);this.code = code;
        this.referenceId = referenceId;
        this.location = location;
        this.organizer = organizer;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public MeetingAttendence code(String code) {
        this.code = code;
        return this;
    }

    public MeetingAttendence referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public MeetingAttendence location(String location) {
        this.location = location;
        return this;
    }

    public MeetingAttendence organizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public MeetingAttendence title(String title) {
        this.title = title;
        return this;
    }

    public MeetingAttendence description(String description) {
        this.description = description;
        return this;
    }

    public MeetingAttendence startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public MeetingAttendence endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MeetingAttendence)) {
            return false;
        }
        MeetingAttendence meetingAttendence = (MeetingAttendence) o;
        return Objects.equals(code, meetingAttendence.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, referenceId, location, organizer, title, description, startTime, endTime);
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", referenceId='" + getReferenceId() + "'" +
            ", location='" + getLocation() + "'" +
            ", organizer='" + getOrganizer() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }

}
