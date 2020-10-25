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

    @Column(name = "title", length = 100)
    protected String title;

    @Column(name = "description")
    protected String description;

    public MeetingAttendence() {
        setType("MEETING");
    }

    public MeetingAttendence(String id, User user, ZonedDateTime dateTime, String action, double latitude, double longitude, boolean isMockLocation, String picturePath, 
        String code, String referenceId, String title, String description) {
        super(user, "MEETING", dateTime, action, latitude, longitude, isMockLocation, picturePath);
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MeetingAttendence(String code, String referenceId, String title, String description) {
        this.code = code;
        this.referenceId = referenceId;
        this.title = title;
        this.description = description;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
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

    public MeetingAttendence code(String code) {
        this.code = code;
        return this;
    }

    public MeetingAttendence referenceId(String referenceId) {
        this.referenceId = referenceId;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MeetingAttendence)) {
            return false;
        }
        Attendence meetingAttendence = (Attendence) o;
        return super.equals(meetingAttendence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.getId(), type, dateTime, action, latitude, longitude, isMockLocation, picturePath, code, title, description);
    }

    @Override
    public String toString() {
        return "{" + super.toString() +   
            ", code='" + getCode() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }


}
