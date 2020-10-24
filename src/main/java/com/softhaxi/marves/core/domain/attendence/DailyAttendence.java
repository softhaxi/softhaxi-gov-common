package com.softhaxi.marves.core.domain.attendence;

import java.sql.Timestamp;
import java.util.Objects;

import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class DailyAttendence extends Attendence {

    /**
     *
     */
    private static final long serialVersionUID = -3657340582284188254L;
    protected String inWork;
    protected String outAction;
    protected Timestamp outDateTime;
    protected double outLatitude;
    protected double outLongitude;
    protected boolean isOutMockLocation;
    protected String outPicturePath;
    protected String outWork;
    


    public DailyAttendence() {
    }

    public DailyAttendence(String id, User user, Timestamp dateTime, String action, double latitude, double longitude, boolean isMockLocation, String picturePath, 
        String inWork, String outAction, Timestamp outDateTime, double outLatitude, double outLongitude, boolean isOutMockLocation, String outPicturePath,
        String outWork) {
        super(id, user, "DAILY", dateTime, action, latitude, longitude, isMockLocation, picturePath);
        this.inWork = inWork;
        this.outAction = outAction;
        this.outDateTime = outDateTime;
        this.outLatitude = outLatitude;
        this.outLongitude = outLongitude;
        this.isOutMockLocation = isOutMockLocation;
        this.outPicturePath = outPicturePath;
        this.outWork = outWork;
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

    public Timestamp getOutDateTime() {
        return this.outDateTime;
    }

    public void setOutDateTime(Timestamp outDateTime) {
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

    public DailyAttendence inWork(String inWork) {
        this.inWork = inWork;
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

    public DailyAttendence outLatitude(double outLatitude) {
        this.outLatitude = outLatitude;
        return this;
    }

    public DailyAttendence outLongitude(double outLongitude) {
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

    public DailyAttendence outWork(String outWork) {
        this.outWork = outWork;
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
        return Objects.hash(super.hashCode(), inWork, outAction, outDateTime, outLatitude, outLongitude, isOutMockLocation, outPicturePath, outWork);
    }

    @Override
    public String toString() {
        return "{" + super.toString() +
            ", outAction='" + getInWork() + "'" +
            ", outAction='" + getOutAction() + "'" +
            ", outDateTime='" + getOutDateTime() + "'" +
            ", outLatitude='" + getOutLatitude() + "'" +
            ", outLongitude='" + getOutLongitude() + "'" +
            ", isOutMockLocation='" + isOutMockLocation() + "'" +
            ", outPicturePath='" + getOutPicturePath() + "'" +
            ", outAction='" + getOutWork() + "'" +
            "}";
    }
    
}
