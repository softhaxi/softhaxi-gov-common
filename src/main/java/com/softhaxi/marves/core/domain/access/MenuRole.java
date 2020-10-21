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
@Table(name = "menu_role")
@Access(value = AccessType.FIELD)
public class MenuRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7940062212428766722L;
    
    @NotBlank
    @Column(name = "id", length=40)
    protected String id;
    
    @OneToOne(mappedBy = "menu")
    @JoinColumn(name="menu_id", referencedColumnName="id")
    protected Menu menu;
    
    @OneToOne(mappedBy = "role")
    @JoinColumn(name="role_id", referencedColumnName="id")
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