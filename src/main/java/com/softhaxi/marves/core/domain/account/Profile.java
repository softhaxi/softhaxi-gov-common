package com.softhaxi.marves.core.domain.account;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "profiles")
@Access(value = AccessType.FIELD)
public class Profile implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2215408560873289635L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
	@Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user; 
    
    protected User owner;
    protected String firstName;
    protected String lastName;

    @Column(name = "full_name", nullable = false, length = 100)
    protected String fullName;
    protected String gender;
    protected String nationality;
    protected String identityID;
    protected String identityType;

    @Column(name = "primary_email", length = 50)
    protected String primaryEmail;
    protected String primaryMobile;

    @Column(name = "status", length = 10)
    protected String status = "ACTIVE";


    public Profile() {
    }

    public Profile(User user, User owner, String firstName, String lastName, String fullName, String gender, String nationality, String identityID, String identityType, String primaryEmail, String primaryMobile, String status) {
        this.user = user;
        this.owner = owner;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.gender = gender;
        this.nationality = nationality;
        this.identityID = identityID;
        this.identityType = identityType;
        this.primaryEmail = primaryEmail;
        this.primaryMobile = primaryMobile;
        this.status = status;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdentityID() {
        return this.identityID;
    }

    public void setIdentityID(String identityID) {
        this.identityID = identityID;
    }

    public String getIdentityType() {
        return this.identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getPrimaryEmail() {
        return this.primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryMobile() {
        return this.primaryMobile;
    }

    public void setPrimaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Profile id(UUID id) {
        this.id = id;
        return this;
    }

    public Profile user(User user) {
        this.user = user;
        return this;
    }

    public Profile owner(User owner) {
        this.owner = owner;
        return this;
    }

    public Profile firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Profile lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Profile fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Profile gender(String gender) {
        this.gender = gender;
        return this;
    }

    public Profile nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public Profile identityID(String identityID) {
        this.identityID = identityID;
        return this;
    }

    public Profile identityType(String identityType) {
        this.identityType = identityType;
        return this;
    }

    public Profile primaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
        return this;
    }

    public Profile primaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
        return this;
    }

    public Profile status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, owner, firstName, lastName, fullName, gender, nationality, identityID, identityType, primaryEmail, primaryMobile, status);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", owner='" + getOwner() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", gender='" + getGender() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", identityID='" + getIdentityID() + "'" +
            ", identityType='" + getIdentityType() + "'" +
            ", primaryEmail='" + getPrimaryEmail() + "'" +
            ", primaryMobile='" + getPrimaryMobile() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
    
}
