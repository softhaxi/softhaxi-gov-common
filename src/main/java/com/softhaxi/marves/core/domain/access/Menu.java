package com.softhaxi.marves.core.domain.access;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class Menu implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6788145488623537446L;
    protected String id;
    protected String code;
    protected String name;
    protected String description;
    
    public Menu() {

    }
    

    public Menu(String id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu id(String id) {
        this.id = id;
        return this;
    }

    public Menu code(String code) {
        this.code = code;
        return this;
    }

    public Menu name(String name) {
        this.name = name;
        return this;
    }

    public Menu description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Menu)) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) && Objects.equals(code, menu.code) && Objects.equals(name, menu.name) && Objects.equals(description, menu.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, description);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
}