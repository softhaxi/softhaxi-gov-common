package com.softhaxi.marves.core.domain.attendance;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "daily_attendances")
@Access(value = AccessType.FIELD)
public class DailyAttendance extends Attendance {

    /**
     *
     */
    private static final long serialVersionUID = -3657340582284188254L;

    @Column(name = "work_from", length = 10)
    protected String workFrom;

    @Column(name = "in_work", length = 10)
    protected String inWork;

    @Column(name = "action", length = 10)
    protected String outAction;

    @Column(name = "date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime outDateTime;

    @Column(name = "latitude", nullable = true)
    protected double outLatitude;

    @Column(name = "longitude", nullable = true)
    protected double outLongitude;

    @Column(name = "is_mock_location", nullable = true)
    protected boolean isOutMockLocation;

    @Column(name = "picture_path")
    protected String outPicturePath;

    @Column(name = "out_work", length = 10)
    protected String outWork;
    
    @Column(name = "ip_address")
    protected String outIpAddress;

    @Column(name = "dispensation_code")
    protected String dispensationCode;

    @Column(name = "dispensation_type")
    protected String dispensationType;

    @Column(name = "dispensation_file")
    protected ZonedDateTime dispensationFile;
    
    public DailyAttendance() {
        setType("DAILY");
    }

    public String getWorkFrom() {
        return this.workFrom;
    }

    public void setWorkFrom(String workFrom) {
        this.workFrom = workFrom;
    }

    public String getInWork() {
        return this.inWork;
    }

    public void setInWork(String inWork) {
        this.inWork = inWork;
    }

    public String getOutAction() {
        return this.outAction;
    }

    public void setOutAction(String outAction) {
        this.outAction = outAction;
    }

    public ZonedDateTime getOutDateTime() {
        return this.outDateTime;
    }

    public void setOutDateTime(ZonedDateTime outDateTime) {
        this.outDateTime = outDateTime;
    }

    public double getOutLatitude() {
        return this.outLatitude;
    }

    public void setOutLatitude(double outLatitude) {
        this.outLatitude = outLatitude;
    }

    public double getOutLongitude() {
        return this.outLongitude;
    }

    public void setOutLongitude(double outLongitude) {
        this.outLongitude = outLongitude;
    }

    public boolean isOutMockLocation() {
        return this.isOutMockLocation;
    }

    public void setIsOutMockLocation(boolean isOutMockLocation) {
        this.isOutMockLocation = isOutMockLocation;
    }

    public String getOutPicturePath() {
        return this.outPicturePath;
    }

    public void setOutPicturePath(String outPicturePath) {
        this.outPicturePath = outPicturePath;
    }

    public String getOutWork() {
        return this.outWork;
    }

    public void setOutWork(String outWork) {
        this.outWork = outWork;
    }

    public String getOutIpAddress() {
        return this.outIpAddress;
    }

    public void setOutIpAddress(String outIpAddress) {
        this.outIpAddress = outIpAddress;
    }

    public String getDispensationCode() {
        return this.dispensationCode;
    }

    public void setDispensationCode(String dispensationCode) {
        this.dispensationCode = dispensationCode;
    }

    public String getDispensationType() {
        return this.dispensationType;
    }

    public void setDispensationType(String dispensationType) {
        this.dispensationType = dispensationType;
    }

    public ZonedDateTime getDispensationFile() {
        return this.dispensationFile;
    }

    public void setDispensationFile(ZonedDateTime dispensationFile) {
        this.dispensationFile = dispensationFile;
    }

    public DailyAttendance workFrom(String workFrom) {
        this.workFrom = workFrom;
        return this;
    }

    public DailyAttendance inWork(String inWork) {
        this.inWork = inWork;
        return this;
    }

    public DailyAttendance outAction(String outAction) {
        this.outAction = outAction;
        return this;
    }

    public DailyAttendance outDateTime(ZonedDateTime outDateTime) {
        this.outDateTime = outDateTime;
        return this;
    }

    public DailyAttendance outLatitude(double outLatitude) {
        this.outLatitude = outLatitude;
        return this;
    }

    public DailyAttendance outLongitude(double outLongitude) {
        this.outLongitude = outLongitude;
        return this;
    }

    public DailyAttendance isOutMockLocation(boolean isOutMockLocation) {
        this.isOutMockLocation = isOutMockLocation;
        return this;
    }

    public DailyAttendance outPicturePath(String outPicturePath) {
        this.outPicturePath = outPicturePath;
        return this;
    }

    public DailyAttendance outWork(String outWork) {
        this.outWork = outWork;
        return this;
    }

    public DailyAttendance outIpAddress(String outIpAddress) {
        setOutIpAddress(outIpAddress);
        return this;
    }

    public DailyAttendance dispensationCode(String dispensationCode) {
        setDispensationCode(dispensationCode);
        return this;
    }

    public DailyAttendance dispensationType(String dispensationType) {
        setDispensationType(dispensationType);
        return this;
    }

    public DailyAttendance dispensationFile(ZonedDateTime dispensationFile) {
        setDispensationFile(dispensationFile);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DailyAttendance)) {
            return false;
        }
        Attendance dailyAttendence = (Attendance) o;
        return super.equals(dailyAttendence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workFrom, inWork, outAction, outDateTime, outLatitude, outLongitude, isOutMockLocation, outPicturePath, outWork);
    }

    @Override
    public String toString() {
        return "{" + super.toString() +
            ", workFrom='" + getWorkFrom() + "'" +
            ", inWork='" + getInWork() + "'" +
            ", outAction='" + getOutAction() + "'" +
            ", outDateTime='" + getOutDateTime() + "'" +
            ", outLatitude='" + getOutLatitude() + "'" +
            ", outLongitude='" + getOutLongitude() + "'" +
            ", isOutMockLocation='" + isOutMockLocation() + "'" +
            ", outPicturePath='" + getOutPicturePath() + "'" +
            ", outWork='" + getOutWork() + "'" +
            ", dispensationCode='" + getDispensationCode() + "'" +
            ", dispensationType='" + getDispensationType() + "'" +
            ", dispensationFile='" + getDispensationFile() + "'" +
            "}";
    }
}