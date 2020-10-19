package com.softhaxi.marves.core.domain.account;

import java.io.Serializable;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class Profile implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2215408560873289635L;
    protected String id;
    protected User user;
    protected User owner;
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String nationality;
    protected String identityID;
    protected String identityType;
    protected String primaryEmail;
    protected String primaryMobile;
    protected String status;

    /**
     * 
     */
    public Profile() {
        this(null, null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * 
     * @param id
     * @param user
     * @param owner
     * @param firstName
     * @param lastName
     * @param gender
     * @param nationality
     * @param identityID
     * @param identityType
     * @param primaryEmail
     * @param primaryMobile
     * @param status 
     */
    public Profile(String id, User user, User owner, String firstName, 
            String lastName, String gender, String nationality, String identityID, 
            String identityType, String primaryEmail, String primaryMobile, 
            String status) {
        this.id = id;
        this.user = user;
        this.owner = owner;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.identityID = identityID;
        this.identityType = identityType;
        this.primaryEmail = primaryEmail;
        this.primaryMobile = primaryMobile;
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the identityID
     */
    public String getIdentityID() {
        return identityID;
    }

    /**
     * @param identityID the identityID to set
     */
    public void setIdentityID(String identityID) {
        this.identityID = identityID;
    }

    /**
     * @return the identityType
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * @param identityType the identityType to set
     */
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    /**
     * @return the primaryEmail
     */
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    /**
     * @param primaryEmail the primaryEmail to set
     */
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    /**
     * @return the primaryMobile
     */
    public String getPrimaryMobile() {
        return primaryMobile;
    }

    /**
     * @param primaryMobile the primaryMobile to set
     */
    public void setPrimaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Profile{" 
                + "id=" + id 
                + ", user=" + user 
                + ", owner=" + owner 
                + ", firstName=" + firstName 
                + ", lastName=" + lastName 
                + ", gender=" + gender 
                + ", nationality=" + nationality 
                + ", identityID=" + identityID 
                + ", identityType=" + identityType 
                + ", primaryEmail=" + primaryEmail 
                + ", primaryMobile=" + primaryMobile 
                + ", status=" + status 
                + '}';
    }
    
    
}
