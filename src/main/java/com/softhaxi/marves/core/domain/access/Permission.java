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

/**
 * @author Raja Sihombing
 * @since 1
 */

@Entity
@Table(name = "permission")
@Access(value = AccessType.FIELD)
public class Permission implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5421976578750521127L;
    
    @NotBlank
    @Column(name = "id", length=40)
    protected String id;
    
    @OneToOne(mappedBy = "role")
    @JoinColumn(name="role_id", referencedColumnName="id")
    protected Role role;
    
    @OneToOne(mappedBy = "scope")
    @JoinColumn(name="scope_id", referencedColumnName="id")
    protected Scope scope;
    
    /**
     * 
     */
    public Permission() {
        this(null, null, null);
    }

    /**
     * 
     * @param id
     * @param role
     * @param scope 
     */
    public Permission(String id, Role role, Scope scope) {
        this.id = id;
        this.role = role;
        this.scope = scope;
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
     * @return the scope
     */
    public Scope getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(Scope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Permission{" 
                + "id=" + id 
                + ", role=" + role 
                + ", scope=" + scope 
                + '}';
    }
}
