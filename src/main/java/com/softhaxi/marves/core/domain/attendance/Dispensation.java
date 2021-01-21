package com.softhaxi.marves.core.domain.attendance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.account.User;

@Entity
@Table(name = "dispensations")
@Access(value = AccessType.FIELD)
public class Dispensation extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4158071257283946443L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "type", nullable = false, length = 50)
    protected String type = "LEAVE";

    @Column(name = "code", nullable = true, length = 50)
    protected String code;

    @Column(name = "start_date")
    protected LocalDate startDate;

    @Column(name = "end_date")
    protected LocalDate endDate;

    @Column(name = "file_name")
    protected String fileName;

    @JsonIgnore
    @Column(name = "attachment")
    protected String attachment;

    @Column(name = "ip_address")
    protected String ipAddress;


    public Dispensation() {
    }

    public Dispensation(User user, String type, String code, LocalDate startDate, LocalDate endDate, String fileName, String attachment, String ipAddress) {
        this.user = user;
        this.type = type;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fileName = fileName;
        this.attachment = attachment;
        this.ipAddress = ipAddress;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Dispensation user(User user) {
        setUser(user);
        return this;
    }

    public Dispensation type(String type) {
        setType(type);
        return this;
    }

    public Dispensation code(String code) {
        setCode(code);
        return this;
    }

    public Dispensation startDate(LocalDate startDate) {
        setStartDate(startDate);
        return this;
    }

    public Dispensation endDate(LocalDate endDate) {
        setEndDate(endDate);
        return this;
    }

    public Dispensation fileName(String fileName) {
        setFileName(fileName);
        return this;
    }

    public Dispensation attachment(String attachment) {
        setAttachment(attachment);
        return this;
    }

    public Dispensation ipAddress(String ipAddress) {
        setIpAddress(ipAddress);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Dispensation)) {
            return false;
        }
        Dispensation dispensation = (Dispensation) o;
        return Objects.equals(id, dispensation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, code, startDate, endDate, fileName, attachment, ipAddress);
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser().getId() + "'" +
            ", type='" + getType() + "'" +
            ", code='" + getCode() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", attachment='" + getAttachment() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            "}";
    }

}
