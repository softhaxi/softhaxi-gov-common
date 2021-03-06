package com.softhaxi.marves.core.domain.master;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softhaxi.marves.core.domain.Auditable;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "sysparams")
@Access(value = AccessType.FIELD)
public class SystemParameter extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6724979328243935740L;
    
    @Column(name = "code", nullable = false, length = 50)
    protected String code;

    @Column(name = "name", nullable = true, length = 100)
    protected String name;

    @Column(name = "description", nullable = true)
    protected String decription;

    @Column(name = "value", nullable = true)
    protected String value;

    @JsonIgnore
    @Column(name = "regex", nullable = true)
    protected String regex;

    @Column(name = "additional_info", nullable = true)
    protected String additionalInfo;

    @JsonIgnore
    @Column(name = "is_system")
    protected boolean isSystem = false;

    @JsonIgnore
    @Column(name = "is_editable")
    protected boolean isEditable = true;

    @JsonIgnore
    @Column(name = "is_deleted")
    protected boolean isDeleted;

    public SystemParameter() {
    }

    public SystemParameter(String code, String name, String decription, String value, String regex, String additionalInfo, boolean isSystem, boolean isEditable, boolean isDeleted) {
        this.code = code;
        this.name = name;
        this.decription = decription;
        this.value = value;
        this.regex = regex;
        this.additionalInfo = additionalInfo;
        this.isSystem = isSystem;
        this.isEditable = isEditable;
        this.isDeleted = isDeleted;
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

    public String getDecription() {
        return this.decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegex() {
        return this.regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public boolean isSystem() {
        return this.isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public boolean isEditable() {
        return this.isEditable;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public SystemParameter id(UUID id) {
        this.id = id;
        return this;
    }

    public SystemParameter code(String code) {
        this.code = code;
        return this;
    }

    public SystemParameter name(String name) {
        this.name = name;
        return this;
    }

    public SystemParameter decription(String decription) {
        this.decription = decription;
        return this;
    }

    public SystemParameter value(String value) {
        this.value = value;
        return this;
    }

    public SystemParameter regex(String regex) {
        this.regex = regex;
        return this;
    }

    public SystemParameter additionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public SystemParameter isSystem(boolean isSystem) {
        this.isSystem = isSystem;
        return this;
    }

    public SystemParameter isEditable(boolean isEditable) {
        this.isEditable = isEditable;
        return this;
    }

    public SystemParameter isDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SystemParameter)) {
            return false;
        }
        SystemParameter systemParameter = (SystemParameter) o;
        return Objects.equals(id, systemParameter.id) || Objects.equals(code, systemParameter.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, value, regex, additionalInfo, isSystem, isEditable, isDeleted);
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", regex='" + getRegex() + "'" +
            ", regex='" + getAdditionalInfo() + "'" +
            ", isSystem='" + isSystem() + "'" +
            ", isEditable='" + isEditable() + "'" +
            ", isDeleted='" + isDeleted() + "'" +
            "}";
    }

}
