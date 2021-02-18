package com.softhaxi.marves.core.model.employee;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.softhaxi.marves.core.enums.DispensationType;

public class Absence implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8317903104459307035L;
    private UUID id;
    private UUID userId;
    private String email;
    private String fullName;
    private String divisionName;
    private String workFrom;
    private LocalDate date;
    private ZonedDateTime clockInTime;
    private String clockInIpAddress;
    private boolean clockInMockLocation;
    private ZonedDateTime clockOutTime;
    private String clockOutIpAddress;
    private boolean clockOutMockLocation;
    private UUID dispensationId;
    private String dispensation;
    private String dispensationReason;
    private boolean weekend;



    public Absence() {
    }

    public Absence(UUID id, UUID userId, String email, String fullName, String divisionName, String workFrom, LocalDate date, ZonedDateTime clockInTime, String clockInIpAddress, boolean clockInMockLocation, ZonedDateTime clockOutTime, String clockOutIpAddress, boolean clockOutMockLocation, UUID dispensationId, String dispensation, String dispensationReason, boolean weekend) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.divisionName = divisionName;
        this.workFrom = workFrom;
        this.date = date;
        this.clockInTime = clockInTime;
        this.clockInIpAddress = clockInIpAddress;
        this.clockInMockLocation = clockInMockLocation;
        this.clockOutTime = clockOutTime;
        this.clockOutIpAddress = clockOutIpAddress;
        this.clockOutMockLocation = clockOutMockLocation;
        this.dispensationId = dispensationId;
        this.dispensation = dispensation;
        this.dispensationReason = dispensationReason;
        this.weekend = weekend;
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

    public String getWorkFrom() {
        return this.workFrom;
    }

    public void setWorkFrom(String workFrom) {
        this.workFrom = workFrom;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    } 

    public ZonedDateTime getClockInTime() {
        return this.clockInTime;
    }

    public void setClockInTime(ZonedDateTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getClockInIpAddress() {
        return this.clockInIpAddress;
    }

    public void setClockInIpAddress(String clockInIpAddress) {
        this.clockInIpAddress = clockInIpAddress;
    }

    public boolean isClockInMockLocation() {
        return this.clockInMockLocation;
    }

    public boolean getClockInMockLocation() {
        return this.clockInMockLocation;
    }

    public void setClockInMockLocation(boolean clockInMockLocation) {
        this.clockInMockLocation = clockInMockLocation;
    }

    public ZonedDateTime getClockOutTime() {
        return this.clockOutTime;
    }

    public void setClockOutTime(ZonedDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getClockOutIpAddress() {
        return this.clockOutIpAddress;
    }

    public void setClockOutIpAddress(String clockOutIpAddress) {
        this.clockOutIpAddress = clockOutIpAddress;
    }

    public boolean isClockOutMockLocation() {
        return this.clockOutMockLocation;
    }

    public boolean getClockOutMockLocation() {
        return this.clockOutMockLocation;
    }

    public void setClockOutMockLocation(boolean clockOutMockLocation) {
        this.clockOutMockLocation = clockOutMockLocation;
    }

    public UUID getDispensationId() {
        return this.dispensationId;
    }

    public void setDispensationId(UUID dispensationId) {
        this.dispensationId = dispensationId;
    }

    public String getDispensation() {
        return this.dispensation;
    }

    public void setDispensation(String dispensation) {
        this.dispensation = dispensation;
    }

    public String getDispensationReason() {
        return this.dispensationReason;
    }

    public void setDispensationReason(String dispensationReason) {
        this.dispensationReason = dispensationReason;
    }
    
    public boolean isWeekend() {
        return this.weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }  

    public String getStatus() {
        if(this.weekend) return "Weekend";
        if (this.dispensationId != null) {
            DispensationType type = DispensationType.valueOf(this.dispensation);
            if (type.equals(DispensationType.OTHERS)) {
                return dispensationReason;
            }
            return type.getValue();
        }
        return this.workFrom != null ? this.workFrom.toUpperCase() : null;
    }

    public boolean isFakeLocator() {
        if (this.clockInTime != null) {
            if (this.clockOutTime != null) {
                return this.clockInMockLocation || this.clockOutMockLocation;
            } else
                return this.clockInMockLocation;
        }
        return false;
    }

    public String workingDuration() {
        LocalTime startTime = null;
        LocalTime endTime = null;
        if (this.clockInTime != null) {
            startTime = LocalTime.ofInstant(this.clockInTime.toInstant(), ZoneOffset.systemDefault());
        }

        if (startTime != null) {

            if (this.clockOutTime != null) {
                endTime = LocalTime.ofInstant(this.clockOutTime.toInstant(), ZoneOffset.systemDefault());
            } else {
                if (!clockInTime.toLocalDate().equals(LocalDate.now())) {
                    return null;
                }
                endTime = LocalTime.now();
            }
            Duration duration = Duration.between(startTime, endTime);
            long seconds = duration.getSeconds();
            long absSeconds = Math.abs(seconds);
            String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60,
                    absSeconds % 60);
            return positive;
        }
        return null;
    }

    public Absence id(UUID id) {
        setId(id);
        return this;
    }

    public Absence userId(UUID userId) {
        setUserId(userId);
        return this;
    }

    public Absence email(String email) {
        setEmail(email);
        return this;
    }

    public Absence fullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    public Absence divisionName(String divisionName) {
        setDivisionName(divisionName);
        return this;
    }

    public Absence workFrom(String workFrom) {
        setWorkFrom(workFrom);
        return this;
    }
    
    public Absence date(LocalDate date) {
        setDate(date);
        return this;
    } 

    public Absence clockInTime(ZonedDateTime clockInTime) {
        setClockInTime(clockInTime);
        return this;
    }

    public Absence clockInIpAddress(String clockInIpAddress) {
        setClockInIpAddress(clockInIpAddress);
        return this;
    }

    public Absence clockInMockLocation(boolean clockInMockLocation) {
        setClockInMockLocation(clockInMockLocation);
        return this;
    }

    public Absence clockOutTime(ZonedDateTime clockOutTime) {
        setClockOutTime(clockOutTime);
        return this;
    }

    public Absence clockOutIpAddress(String clockOutIpAddress) {
        setClockOutIpAddress(clockOutIpAddress);
        return this;
    }

    public Absence clockOutMockLocation(boolean clockOutMockLocation) {
        setClockOutMockLocation(clockOutMockLocation);
        return this;
    }

    public Absence dispensationId(UUID dispensationId) {
        setDispensationId(dispensationId);
        return this;
    }

    public Absence dispensation(String dispensation) {
        setDispensation(dispensation);
        return this;
    }

    public Absence dispensationReason(String dispensationReason) {
        setDispensationReason(dispensationReason);
        return this;
    }

    public Absence weekend(boolean weekend) {
        setWeekend(weekend);
        return this;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", userId='" + getUserId() + "'" + ", email='" + getEmail() + "'"
                + ", fullName='" + getFullName() + "'" + ", divisionName='" + getDivisionName() + "'" + ", workFrom='"
                + getWorkFrom() + "'" + ", clockInTime='" + getClockInTime() + "'" + ", clockInIpAddress='"
                + getClockInIpAddress() + "'" + ", clockInMockLocation='" + isClockInMockLocation() + "'"
                + ", clockOutTime='" + getClockOutTime() + "'" + ", clockOutIpAddress='" + getClockOutIpAddress() + "'"
                + ", clockOutMockLocation='" + isClockOutMockLocation() + "'" + ", dispensationId='"
                + getDispensationId() + "'" + ", dispensation='" + getDispensation() + "'" + ", dispensationReason='"
                + getDispensationReason() + "'" + "}";
    }

}
