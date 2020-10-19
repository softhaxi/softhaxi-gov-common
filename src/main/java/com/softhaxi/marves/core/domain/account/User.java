package com.softhaxi.marves.core.domain.account;

import java.io.Serializable;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7769293153652418675L;
    protected String id;
    protected String username;
    protected String email;
    protected String mobile;
    protected String password;
    protected String status;
    protected int noLoginFailed;
    protected String passphrase;
    protected String imageSecurity;
    
    /**
     * 
     */
    public User() {
        this(null, null, null, null, null, null, -1, null, null);
    }

    public User(String id, String username, String email, String mobile, 
            String password, String status, int noLoginFailed, 
            String passphrase, String imageSecurity) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.status = status;
        this.noLoginFailed = noLoginFailed;
        this.passphrase = passphrase;
        this.imageSecurity = imageSecurity;
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

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "User{" 
                + "id=" + id 
                + ", username=" + username 
                + ", email=" + email 
                + ", mobile=" + mobile 
                + ", password=" + password 
                + ", status=" + status 
                + ", noLoginFailed=" + noLoginFailed 
                + ", passphrase=" + passphrase 
                + ", imageSecurity=" + imageSecurity 
                + '}';
    }
    
    
}
