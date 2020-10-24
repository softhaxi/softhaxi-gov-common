package com.softhaxi.marves.core.domain.master;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "offices")
@Access(value = AccessType.FIELD)
public class Office implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4270860244610567774L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
	@Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @Column(name = "code", nullable = true, length = 20)
    protected String code;
    
    @Column(name = "name", nullable = false, length = 100)
    protected String name;

    @Column(name = "street", length = 100)
    protected String street;

    @Column(name = "state", length = 100)
    protected String state;

    @Column(name = "city", length = 100)
    protected String city;

    @Column(name = "zip_code", length = 10)
    protected String zipCode;

    @Column(name = "type", length = 100)
    protected String type;

    @Column(name = "latitude", nullable = false)
    protected double latitude = 0.0;

    @Column(name = "longitude", nullable = false)
    protected double longitude = 0.0;
    

    public Office() {
    }

    public Office(String code, String name, String street, String state, String city, String zipCode, String type, double latitude, double longitude) {
        this.code = code;
        this.name = name;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Office id(UUID id) {
        this.id = id;
        return this;
    }

    public Office code(String code) {
        this.code = code;
        return this;
    }

    public Office name(String name) {
        this.name = name;
        return this;
    }

    public Office street(String street) {
        this.street = street;
        return this;
    }

    public Office state(String state) {
        this.state = state;
        return this;
    }

    public Office city(String city) {
        this.city = city;
        return this;
    }

    public Office zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public Office type(String type) {
        this.type = type;
        return this;
    }

    public Office latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Office longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Office)) {
            return false;
        }
        Office office = (Office) o;
        return Objects.equals(id, office.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, street, state, city, zipCode, type, latitude, longitude);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", street='" + getStreet() + "'" +
            ", state='" + getState() + "'" +
            ", city='" + getCity() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", type='" + getType() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }
    
}
