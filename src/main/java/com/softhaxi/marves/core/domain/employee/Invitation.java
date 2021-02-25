package com.softhaxi.marves.core.domain.employee;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "invitations")
@Access(value = AccessType.FIELD)
public class Invitation extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5354619638930183120L;

    @Column(name = "type", nullable = false, length = 50)
    protected String type = "TASK";

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "code", nullable = true, length = 50)
    protected String code;

    @Column(name = "title", nullable = false, length = 100)
    protected String title; 

    @Column(name = "description", nullable = true)
    protected String description;

    @Column(name = "location")
    protected String location;

    @Column(name = "category")
    protected String category = "INOFFICE";

    @Column(name = "start_date")
    protected LocalDate startDate;

    @Column(name = "end_date")
    protected LocalDate endDate;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    protected ZonedDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = true)
    protected ZonedDateTime endTime;

    // @Column(name = "start_time_mobile", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    // protected ZonedDateTime startTimeMobile;

    // @Column(name = "end_time_mobile", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    // protected ZonedDateTime endTimeMobile;

    @Column(name = "latitude")
    protected double latitude;

    @Column(name = "longitude")
    protected double longitude;

    @Column(name = "file_name")
    protected String fileName;

    @JsonIgnore
    @Column(name = "attachment")
    protected String attachement;

    @JsonIgnore
    @Column(name = "is_deleted")
    protected boolean deleted;

    @JsonIgnore
    @OneToMany(mappedBy = "invitation", fetch = FetchType.LAZY)
    protected Set<InvitationMember> invitees;

    @Transient
    protected Set<Map<String, Object>> members;

    @Transient
    protected boolean completed;


    public Invitation() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    // public ZonedDateTime getStartTimeMobile() {
    //     return this.startTimeMobile;
    // }

    // public void setStartTimeMobile(ZonedDateTime startTimeMobile) {
    //     this.startTimeMobile = startTimeMobile;
    // }

    // public ZonedDateTime getEndTimeMobile() {
    //     return this.endTimeMobile;
    // }

    // public void setEndTimeMobile(ZonedDateTime endTimeMobile) {
    //     this.endTimeMobile = endTimeMobile;
    // }

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

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachement() {
        return this.attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    public Set<InvitationMember> getInvitees() {
        return this.invitees;
    }

    public void setInvitees(Set<InvitationMember> invitees) {
        this.invitees = invitees;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Map<String,Object>> getMembers() {
        return this.members;
    }

    public void setMembers(Set<Map<String,Object>> members) {
        this.members = members;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getAttachmentUrl() {
        if(getAttachement() == null)
            return null;
        return String.format("/asset%s", getAttachement());
    }

    public Invitation type(String type) {
        setType(type);
        return this;
    }

    public Invitation user(User user) {
        setUser(user);
        return this;
    }

    public Invitation code(String code) {
        setCode(code);
        return this;
    }

    public Invitation title(String title) {
        setTitle(title);
        return this;
    }

    public Invitation description(String description) {
        setDescription(description);
        return this;
    }

    public Invitation location(String location) {
        setLocation(location);
        return this;
    }

    public Invitation category(String category) {
        setCategory(category);
        return this;
    }

    public Invitation startDate(LocalDate startDate) {
        setStartDate(startDate);
        return this;
    }

    public Invitation endDate(LocalDate endDate) {
        setEndDate(endDate);
        return this;
    }

    public Invitation startTime(ZonedDateTime startTime) {
        setStartTime(startTime);
        return this;
    }

    public Invitation endTime(ZonedDateTime endTime) {
        setEndTime(endTime);
        return this;
    }

    // public Invitation startTimeMobile(ZonedDateTime startTimeMobile) {
    //     setStartTimeMobile(startTimeMobile);
    //     return this;
    // }

    // public Invitation endTimeMobile(ZonedDateTime endTimeMobile) {
    //     setEndTimeMobile(endTimeMobile);
    //     return this;
    // }

    public Invitation latitude(double latitude) {
        setLatitude(latitude);
        return this;
    }

    public Invitation longitude(double longitude) {
        setLongitude(longitude);
        return this;
    }
    
    public Invitation fileName(String fileName) {
        setFileName(fileName);
        return this;
    }

    public Invitation attachement(String attachement) {
        setAttachement(attachement);
        return this;
    }

    public Invitation deleted(boolean deleted) {
        setDeleted(deleted);
        return this;
    }

    public Invitation invitees(Set<InvitationMember> invitees) {
        setInvitees(invitees);
        return this;
    }

    public Invitation members(Set<Map<String,Object>> members) {
        setMembers(members);
        return this;
    }

    public Invitation completed(boolean completed) {
        setCompleted(completed);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Invitation)) {
            return false;
        }
        Invitation invitation = (Invitation) o;
        return Objects.equals(id, invitation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, user, code, title, description, location, category, startDate, endDate, startTime, endTime, latitude, longitude, attachement, invitees, members, completed);
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", user='" + getUser() + "'" +
            ", code='" + getCode() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", location='" + getLocation() + "'" +
            ", category='" + getCategory() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", attachement='" + getAttachement() + "'" +
            ", invitees='" + getInvitees() + "'" +
            ", members='" + getMembers() + "'" +
            ", completed='" + isCompleted() + "'" +
            "}";
    }

}
