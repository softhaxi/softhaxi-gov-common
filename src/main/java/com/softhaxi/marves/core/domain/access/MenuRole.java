package com.softhaxi.marves.core.domain.access;

import java.io.Serializable;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class MenuRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7940062212428766722L;
    
    protected String id;
    protected Menu menu;
    protected Role role;


    public MenuRole() {
    }

    public MenuRole(String id, Menu menu, Role role) {
        this.id = id;
        this.menu = menu;
        this.role = role;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", menu='" + getMenu() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }    
}