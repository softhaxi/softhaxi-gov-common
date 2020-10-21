package com.softhaxi.marves.core.domain.access;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Raja Sihombing
 * @since 1
 */

@Entity
@Table(name = "role")
@Access(value = AccessType.FIELD)
public class Role implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6852505996291312103L;
    
    @NotBlank
   	@Column(name = "id", length=40)
    protected String id;
    
    @NotBlank
   	@Column(name = "name", length=40)
    protected String name;
    
    @NotBlank
   	@Column(name = "description", length=200)
    protected String description;
    
    /**
     * 
     */
    public Role() {
        this(null, null, null);
    }

    /**
     * 
     * @param id
     * @param name
     * @param description 
     */
    public Role(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Role{" 
                + "id=" + id 
                + ", name=" + name 
                + ", description=" + description 
                + '}';
    }
    
    
}
