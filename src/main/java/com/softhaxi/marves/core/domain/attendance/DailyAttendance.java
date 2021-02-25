package com.softhaxi.marves.core.domain.attendance;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @Column(name = "date_time_mobile", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected ZonedDateTime outDateTimeMobile;

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

    @Transient
    protected long working;

    @Transient
    protected long late;

    @Transient
    protected long early;

    @Transient 
    protected Dispensation dispensation;

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

    public ZonedDateTime getOutDateTimeMobile() {
        return this.outDateTimeMobile;
    }

    public void setOutDateTimeMobile(ZonedDateTime outDateTimeMobile) {
        this.outDateTimeMobile = outDateTimeMobile;
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

    public String getOutPhotoUrl() {
        if(getOutPicturePath() == null)
            return null;
        return String.format("/asset%s", getOutPicturePath());
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

    public Dispensation getDispensation() {
        return dispensation;
    }

    public void setDispensation(Dispensation dispensation) {
        this.dispensation = dispensation;
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

    public DailyAttendance outDateTimeMobile(ZonedDateTime outDateTimeMobile) {
        this.outDateTimeMobile = outDateTimeMobile;
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

    public long getWorking() {
        return this.working;
    }

    public void setWorking(long working) {
        this.working = working;
    }

    public long getLate() {
        return this.late;
    }

    public void setLate(long late) {
        this.late = late;
    }

    public long getEarly() {
        return this.early;
    }

    public void setEarly(long early) {
        this.early = early;
    }

//     public String getWorkDuration() {
// /*        LocalDateTime localInDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse("07:30:00.00"));
//         LocalDateTime localOutDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse("16:30:00.00"));
//         if(null!=this.dateTime){
//             localInDateTime = LocalDateTime.ofInstant(this.dateTime.toInstant(), ZoneOffset.systemDefault());
//         }
//         if(null!=this.outDateTime){
//             localOutDateTime = LocalDateTime.ofInstant(this.outDateTime.toInstant(), ZoneOffset.systemDefault());
//         }*/

//         LocalTime localTime = LocalTime.parse("07:30:00.00");
//         LocalTime localOutTime = LocalTime.parse("16:30:00.00");
        
//         if(null!=this.dateTime){
//             localTime = LocalTime.ofInstant(this.dateTime.toInstant(), ZoneOffset.systemDefault());
//         }
//         if(null!=this.outDateTime){
//             localOutTime = LocalTime.ofInstant(this.outDateTime.toInstant(), ZoneOffset.systemDefault());
//         }

//         Duration duration = Duration.between(localTime, localOutTime);
//         long seconds = duration.getSeconds();
//         long absSeconds = Math.abs(seconds);
//         String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
//         return seconds < 0 ? "-" + positive : positive;
//     }

//     public boolean isComeLate() {
//         return comeLate;
//     }

//     public void setComeLate(boolean comeLate) {
//         this.comeLate = comeLate;
//     }

//     public boolean isGoBackEarly() {
//         return goBackEarly;
//     }

//     public void setGoBackEarly(boolean goBackEarly) {
//         this.goBackEarly = goBackEarly;
//     }

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
        return Objects.hash(super.hashCode(), workFrom, inWork, outAction, outDateTime, outLatitude, outLongitude,
                isOutMockLocation, outPicturePath, outWork);
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ", workFrom='" + getWorkFrom() + "'" + ", inWork='" + getInWork() + "'"
                + ", outAction='" + getOutAction() + "'" + ", outDateTime='" + getOutDateTime() + "', outDateTimeMobile='" + getOutDateTimeMobile() + "'"
                + ", outLatitude='" + getOutLatitude() + "'" + ", outLongitude='" + getOutLongitude() + "'"
                + ", isOutMockLocation='" + isOutMockLocation() + "'" + ", outPicturePath='" + getOutPicturePath() + "'"
                + ", outWork='" + getOutWork() + "'" + "}";
    }
}