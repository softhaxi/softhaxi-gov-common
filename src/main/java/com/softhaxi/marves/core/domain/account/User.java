package com.softhaxi.marves.core.domain.account;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "users")
@Access(value = AccessType.FIELD)
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7769293153652418675L;
    
    @Id
    @NotBlank
	@Column(name = "id")
    protected String id;
    
    @NotBlank
	@Column(name = "username")
    protected String username;
    
    @NotBlank
	@Column(name = "email")
    protected String email;
    
    @NotBlank
 	@Column(name = "mobile")
    protected String mobile;
    
    @NotBlank
 	@Column(name = "password")
    protected String password;
    
    @NotBlank
 	@Column(name = "status")
    protected String status;
    
    @NotBlank
 	@Column(name = "no_login_failed")
    protected int noLoginFailed;
    
    @NotBlank
 	@Column(name = "passphrase")
    protected String passphrase;
    
    @NotBlank
 	@Column(name = "image_security")
    protected String imageSecurity;
    
    @NotBlank
 	@Column(name = "is_ldap_user")
    protected boolean isLDAPUser;
    
    /**
     * 
     */
    public User() {
        this.isLDAPUser = false;
    }

    public User(String id, String username, String email, String mobile, 
            String password, String status, int noLoginFailed, 
            String passphrase, String imageSecurity, boolean isLDAPUser) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.status = status;
        this.noLoginFailed = noLoginFailed;
        this.passphrase = passphrase;
        this.imageSecurity = imageSecurity;
        this.isLDAPUser = isLDAPUser;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the noLoginFailed
     */
    public int getNoLoginFailed() {
        return noLoginFailed;
    }

    /**
     * @param noLoginFailed the noLoginFailed to set
     */
    public void setNoLoginFailed(int noLoginFailed) {
        this.noLoginFailed = noLoginFailed;
    }

    /**
     * @return the passphrase
     */
    public String getPassphrase() {
        return passphrase;
    }

    /**
     * @param passphrase the passphrase to set
     */
    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    /**
     * @return the imageSecurity
     */
    public String getImageSecurity() {
        return imageSecurity;
    }

    /**
     * @param imageSecurity the imageSecurity to set
     */
    public void setImageSecurity(String imageSecurity) {
        this.imageSecurity = imageSecurity;
    }


    public boolean isLDAPUser() {
        return this.isLDAPUser;
    }

    public void setIsLDAPUser(boolean isLDAPUser) {
        this.isLDAPUser = isLDAPUser;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, mobile, password, status, noLoginFailed, passphrase, imageSecurity, isLDAPUser);
    }
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", password='" + getPassword() + "'" +
            ", status='" + getStatus() + "'" +
            ", noLoginFailed='" + getNoLoginFailed() + "'" +
            ", passphrase='" + getPassphrase() + "'" +
            ", imageSecurity='" + getImageSecurity() + "'" +
            ", isLDAPUser='" + isLDAPUser() + "'" +
            "}";
    }

}
