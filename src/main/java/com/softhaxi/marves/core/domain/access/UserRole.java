package com.softhaxi.marves.core.domain.access;

import com.softhaxi.marves.core.domain.account.User;
import java.io.Serializable;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class UserRole implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4410367904229012076L;
    protected String id;
    protected User user;
    protected Role role;
    
    public UserRole() {
        this(null, null, null);
    }

    /**
     * 
     * @param id
     * @param user
     * @param role 
     */
    public UserRole(String id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
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
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "UserRole{" 
                + "id=" + id 
                + ", user=" + user 
                + ", role=" + role 
                + '}';
    }
}
