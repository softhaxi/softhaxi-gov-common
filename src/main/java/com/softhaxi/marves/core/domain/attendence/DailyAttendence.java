package com.softhaxi.marves.core.domain.attendence;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class DailyAttendence extends Attendence {

    /**
     *
     */
    private static final long serialVersionUID = -3657340582284188254L;
    protected String absence;
    protected String outAction;
    protected Timestamp outDateTime;
    protected float outLatitude;
    protected float outLongitude;
    protected boolean isOutMockLocation;
    protected String outPicturePath;
    protected String outAbsence;


    public DailyAttendence() {
    }

    public DailyAttendence(String id, Timestamp dateTime, String action, float latitude, float longitude, boolean isMockLocation, String picturePath, 
        String absence, String outAction, Timestamp outDateTime, float outLatitude, float outLongitude, boolean isOutMockLocation, String outPicturePath,
        String outAbsence) {
        super(id, "DAILY", dateTime, action, latitude, longitude, isMockLocation, picturePath);
        this.absence = absence;
        this.outAction = outAction;
        this.outDateTime = outDateTime;
        this.outLatitude = outLatitude;
        this.outLongitude = outLongitude;
        this.isOutMockLocation = isOutMockLocation;
        this.outPicturePath = outPicturePath;
        this.outAbsence = outAbsence;
    }


    public String getAbsence() {
        return this.absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }

    public String getOutAction() {
        return this.outAction;
    }

    public void setOutAction(String outAction) {
        this.outAction = outAction;
    }

    public Timestamp getOutDateTime() {
        return this.outDateTime;
    }

    public void setOutDateTime(Timestamp outDateTime) {
        this.outDateTime = outDateTime;
    }

    public float getOutLatitude() {
        return this.outLatitude;
    }

    public void setOutLatitude(float outLatitude) {
        this.outLatitude = outLatitude;
    }

    public float getOutLongitude() {
        return this.outLongitude;
    }

    public void setOutLongitude(float outLongitude) {
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

    public String getOutAbsence() {
        return this.outAbsence;
    }

    public void setOutAbsence(String outAbsence) {
        this.outAbsence = outAbsence;
    }

    public DailyAttendence absence(String absence) {
        this.absence = absence;
        return this;
    }

    public DailyAttendence outAction(String outAction) {
        this.outAction = outAction;
        return this;
    }

    public DailyAttendence outDateTime(Timestamp outDateTime) {
        this.outDateTime = outDateTime;
        return this;
    }

    public DailyAttendence outLatitude(float outLatitude) {
        this.outLatitude = outLatitude;
        return this;
    }

    public DailyAttendence outLongitude(float outLongitude) {
        this.outLongitude = outLongitude;
        return this;
    }

    public DailyAttendence isOutMockLocation(boolean isOutMockLocation) {
        this.isOutMockLocation = isOutMockLocation;
        return this;
    }

    public DailyAttendence outPicturePath(String outPicturePath) {
        this.outPicturePath = outPicturePath;
        return this;
    }

    public DailyAttendence outAbsence(String outAbsence) {
        this.outAbsence = outAbsence;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DailyAttendence)) {
            return false;
        }
        Attendence dailyAttendence = (Attendence) o;
        return super.equals(dailyAttendence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(absence, outAction, outDateTime, outLatitude, outLongitude, isOutMockLocation, outPicturePath, outAbsence);
    }

    @Override
    public String toString() {
        return "{" +
            " absence='" + getAbsence() + "'" +
            ", outAction='" + getOutAction() + "'" +
            ", outDateTime='" + getOutDateTime() + "'" +
            ", outLatitude='" + getOutLatitude() + "'" +
            ", outLongitude='" + getOutLongitude() + "'" +
            ", isOutMockLocation='" + isOutMockLocation() + "'" +
            ", outPicturePath='" + getOutPicturePath() + "'" +
            ", outAbsence='" + getOutAbsence() + "'" +
            "}";
    }
    
}
