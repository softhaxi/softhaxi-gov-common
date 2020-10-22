package com.softhaxi.marves.core.domain.access;

import java.io.Serializable;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class Scope implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6501863899479575955L;
    protected String id;
    protected Scope parent;
    protected String name;
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
