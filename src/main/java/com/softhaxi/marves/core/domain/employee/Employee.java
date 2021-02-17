package com.softhaxi.marves.core.domain.employee;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "employees")
@Access(value = AccessType.FIELD)
public class Employee extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @Column(name = "employee_no", nullable = false, length = 50)
    protected String employeeNo;

    @Column(name = "picture_url")
    protected String pictureUrl;

    @Column(name = "division_name", length = 100)
    protected String divisionName;

    public Employee() {
    }

    public Employee(User user, String employeeNo, String pictureUrl, String divisionName) {
        this.user = user;
        this.employeeNo = employeeNo;
        this.pictureUrl = pictureUrl;
        this.divisionName = divisionName;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmployeeNo() {
        return this.employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDivisionName() {
        return this.divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Employee user(User user) {
        setUser(user);
        return this;
    }

    public Employee employeeNo(String employeeNo) {
        setEmployeeNo(employeeNo);
        return this;
    }

    public Employee pictureUrl(String pictureUrl) {
        setPictureUrl(pictureUrl);
        return this;
    }

    public Employee divisionName(String divisionName) {
        setDivisionName(divisionName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, employeeNo);
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", employeeNo='" + getEmployeeNo() + "'" +
            ", pictureUrl='" + getPictureUrl() + "'" +
            ", divisionName='" + getDivisionName() + "'" +
            "}";
    }

}
