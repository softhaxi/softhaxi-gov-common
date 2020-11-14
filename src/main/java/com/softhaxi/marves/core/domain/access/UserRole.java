package com.softhaxi.marves.core.domain.access;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softhaxi.marves.core.domain.Auditable;
import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "user_roles")
@Access(value = AccessType.FIELD)
public class UserRole extends Auditable<String> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4410367904229012076L;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    protected Role role;

    @Column(name = "description", length = 100)
    protected String description;
    
    public UserRole() {

    }

    /**
     * 
     * @param id
     * @param user
     * @param role 
     */
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
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


    public UserRole(User user, Role role, String description) {
        this.user = user;
        this.role = role;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserRole id(UUID id) {
        this.id = id;
        return this;
    }

    public UserRole user(User user) {
        this.user = user;
        return this;
    }

    public UserRole role(Role role) {
        this.role = role;
        return this;
    }

    public UserRole description(String description) {
        this.description = description;
        return this;
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
