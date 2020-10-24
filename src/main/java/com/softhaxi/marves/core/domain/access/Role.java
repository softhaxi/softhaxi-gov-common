package com.softhaxi.marves.core.domain.access;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "roles")
@Access(value = AccessType.FIELD)
public class Role implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6852505996291312103L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
	@Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @Column(name = "name", nullable = false, length = 50)
    protected String name;

    @Column(name = "description", length = 100)
    protected String description;

    @Column(name = "is_system")
    protected boolean isSystem = false;
    

    public Role() {
    }

    public Role( String name, String description, boolean isSystem) {
        this.name = name;
        this.description = description;
        this.isSystem = isSystem;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isSystem() {
        return this.isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Role id(UUID id) {
        this.id = id;
        return this;
    }

    public Role name(String name) {
        this.name = name;
        return this;
    }

    public Role description(String description) {
        this.description = description;
        return this;
    }

    public Role isSystem(boolean isSystem) {
        this.isSystem = isSystem;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return (Objects.equals(id, role.id) || Objects.equals(name, role.name)) && isSystem == role.isSystem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isSystem);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", isSystem='" + isSystem() + "'" +
            "}";
    }

}
