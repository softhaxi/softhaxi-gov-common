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
@Table(name = "scope")
@Access(value = AccessType.FIELD)
public class Scope implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6501863899479575955L;
    @NotBlank
   	@Column(name = "id", length=40)
    protected String id;
    
    @OneToOne(mappedBy = "parent")
    @JoinColumn(name="parent_id", referencedColumnName="id")
    protected Scope parent;
    
    @NotBlank
   	@Column(name = "name", length=20)
    protected String name;
    
    @NotBlank
   	@Column(name = "description", length=40)
    protected String description;
    
    /**
     * 
     */
    public Scope() {
        this(null, null, null, null);
    }

    /**
     * 
     * @param id
     * @param parent
     * @param name
     * @param description 
     */
    public Scope(String id, Scope parent, String name, String description) {
        this.id = id;
        this.parent = parent;
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
     * @return the parent
     */
    public Scope getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Scope parent) {
        this.parent = parent;
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
        return "Scope{" 
                + "id=" + id 
                + ", parent=" + parent 
                + ", name=" + name 
                + ", description=" + description 
                + '}';
    }
    
    
}
