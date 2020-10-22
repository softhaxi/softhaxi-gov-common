package com.softhaxi.marves.core.domain.master;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class SystemParameter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6724979328243935740L;
    protected String code;
    protected String name;
    protected String value;
    protected String regex;
    protected boolean isSystem;
    protected boolean isEditable;
    protected boolean isDeleted;

    public SystemParameter() {
    }

    public SystemParameter(String code, String name, String value, String regex, boolean isSystem, boolean isEditable, boolean isDeleted) {
        this.code = code;
        this.name = name;
        this.value = value;
        this.regex = regex;
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

    public SystemParameter code(String code) {
        this.code = code;
        return this;
    }

    public SystemParameter name(String name) {
        this.name = name;
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
        return Objects.equals(code, systemParameter.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, value, regex, isSystem, isEditable, isDeleted);
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", regex='" + getRegex() + "'" +
            ", isSystem='" + isSystem() + "'" +
            ", isEditable='" + isEditable() + "'" +
            ", isDeleted='" + isDeleted() + "'" +
            "}";
    }

}
