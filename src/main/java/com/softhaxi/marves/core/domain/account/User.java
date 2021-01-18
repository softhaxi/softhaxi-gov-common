package com.softhaxi.marves.core.domain.account;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.access.UserRole;
import com.softhaxi.marves.core.domain.employee.Employee;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "users")
@Access(value = AccessType.FIELD)
public class User extends Auditable<String> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7769293153652418675L;
	@Column(name = "username", nullable = false, unique = true, length = 50)
    protected String username;
    
	@Column(name = "email", length = 100)
    protected String email;
    
 	@Column(name = "mobile", length = 20)
    protected String mobile;
    
    @NotBlank
 	@Column(name = "password")
    protected String password;
    
 	@Column(name = "status")
    protected String status = "ACTIVE";
    
 	@Column(name = "no_login_failed")
    protected int noLoginFailed = 0;
    
 	@Column(name = "passphrase", length = 20)
    protected String passphrase;
    
 	@Column(name = "image_security")
    protected String imageSecurity;

 	@Column(name = "is_ldap_user")
    protected boolean isLDAPUser = true;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    protected Profile profile;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    protected Employee employee;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    protected Set<UserRole> roles;
    
    /**
     * 
     */
    public User() {

    }

    public User(String username, String email, String mobile, 
            String password, String status, int noLoginFailed, 
            String passphrase, String imageSecurity, boolean isLDAPUser) {
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

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<UserRole> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }


    public User id(UUID id) {
        this.id = id;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User status(String status) {
        this.status = status;
        return this;
    }

    public User noLoginFailed(int noLoginFailed) {
        this.noLoginFailed = noLoginFailed;
        return this;
    }

    public User passphrase(String passphrase) {
        this.passphrase = passphrase;
        return this;
    }

    public User imageSecurity(String imageSecurity) {
        this.imageSecurity = imageSecurity;
        return this;
    }

    public User roles(Set<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public User profile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public User employee(Employee employee) {
        this.employee = employee;
        return this;
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
            " id='" + getId().toString() + "'" +
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
