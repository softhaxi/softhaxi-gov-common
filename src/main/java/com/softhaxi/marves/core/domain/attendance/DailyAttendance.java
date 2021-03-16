package com.softhaxi.marves.core.domain.attendance;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.master.CalendarEvent;
import com.softhaxi.marves.core.enums.employee.DispensationType;

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
    protected ZonedDateTime outExpected;

    @Transient
    protected boolean notAbsence;

    @Transient 
    protected Dispensation dispensation;

    @Transient
    protected CalendarEvent holiday;

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

    public ZonedDateTime getOutExpected() {
        return this.outExpected;
    }

    public void setOutExpected(ZonedDateTime outExpected) {
        this.outExpected = outExpected;
    }

    public boolean isNotAbsence() {
        return this.notAbsence;
    }

    public void setNotAbsence(boolean notAbsence) {
        this.notAbsence = notAbsence;
    }

    public Dispensation getDispensation() {
        return dispensation;
    }

    public void setDispensation(Dispensation dispensation) {
        this.dispensation = dispensation;
    }

    public CalendarEvent getHoliday() {
        return holiday;
    }

    public void setHoliday(CalendarEvent holiday) {
        this.holiday = holiday;
    }

    public boolean isWeekend() {
        if(this.dateTime != null) {
        return this.dateTime.toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY 
            || this.dateTime.toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY;
        }
        return LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY 
        || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public String getStatus() {
        if(isWeekend()) 
            return "Akhir Pekan";

        if(this.holiday != null) return this.holiday.getDescription();
        
        if (this.dispensation != null) {
            DispensationType type = DispensationType.valueOf(this.dispensation.getType());
            if (type.equals(DispensationType.OTHERS)) {
                return String.format("%s (%s)", this.dispensation.getDescription(), "Dispensasi");
            }
            return type.getValue();
        } else if(this.notAbsence) return "Absen";
        
        return this.workFrom != null ? this.workFrom.toUpperCase() : null;
    }

    public boolean isFakeLocator() {
        if (this.dateTime != null) {
            if (this.outDateTime != null) {
                return this.isMockLocation || this.isOutMockLocation;
            } else
                return this.isMockLocation;
        }
        return false;
    }

    public String getWorkingDisplay() {
        if(working != 0) {
            long absSeconds = Math.abs(working);
            String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60,
                    absSeconds % 60);
            return positive;
        }
        return null;
    }

    public String getLateDisplay() {
        if(this.late != 0) {
            long absSeconds = Math.abs(late);
            String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60,
                    absSeconds % 60);
            return positive;
        }
        return null;
    }

    public String getEarlyDisplay() {
        if(this.early != 0) {
            long absSeconds = Math.abs(early);
            String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60,
                    absSeconds % 60);
            return positive;
        }
        return null;
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

    @Override
    public DailyAttendance user(User user) {
        this.user = user;
        return this;
    }

    public DailyAttendance dispensation(Dispensation dispensation) {
        setDispensation(dispensation);
        return this;
    }

    public DailyAttendance outExpected(ZonedDateTime outExpected) {
        setOutExpected(outExpected);
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