package com.softhaxi.marves.core.domain.access;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "user_role")
@Access(value = AccessType.FIELD)
public class UserRole implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4410367904229012076L;
    
    @NotBlank
	@Column(name = "id", length=50)
    protected String id;
    
    @OneToOne(mappedBy = "user")
    @JoinColumn(name="user_id", referencedColumnName="id")
    protected User user;
    
    @OneToOne(mappedBy = "role")
    @JoinColumn(name="role_id", referencedColumnName="id")
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
