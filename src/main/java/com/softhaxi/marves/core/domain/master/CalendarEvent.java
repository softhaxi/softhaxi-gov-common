package com.softhaxi.marves.core.domain.master;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.softhaxi.marves.core.domain.Auditable;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Entity
@Table(name = "calendar_events")
public class CalendarEvent extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3240356752494705745L;

    @Column(name = "type")
    protected String type = "holiday"; // holiday, leave, special leave

    @Column(name = "category")
    protected String category = "national";

    @Column(name = "date", nullable = false)
    protected LocalDate date;

    @Column(name = "time", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = true)
    protected ZonedDateTime time;

    @Column(name = "name", nullable = false)
    protected String name;
    
    @Column(name = "description", nullable = true)
    protected String description;

    @Column(name = "is_deleted")
    protected boolean deleted = false;

    public CalendarEvent() {
    }

    public CalendarEvent(String type, String category, LocalDate date, ZonedDateTime time, String name, String description, boolean deleted) {
        this.type = type;
        this.category = category;
        this.date = date;
        this.time = time;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ZonedDateTime getTime() {
        return this.time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
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

    public boolean isDeleted() {
        return this.deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public CalendarEvent type(String type) {
        setType(type);
        return this;
    }

    public CalendarEvent category(String category) {
        setCategory(category);
        return this;
    }

    public CalendarEvent date(LocalDate date) {
        setDate(date);
        return this;
    }

    public CalendarEvent time(ZonedDateTime time) {
        setTime(time);
        return this;
    }

    public CalendarEvent name(String name) {
        setName(name);
        return this;
    }

    public CalendarEvent description(String description) {
        setDescription(description);
        return this;
    }

    public CalendarEvent deleted(boolean deleted) {
        setDeleted(deleted);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CalendarEvent)) {
            return false;
        }
        CalendarEvent calendarEvent = (CalendarEvent) o;
        return Objects.equals(id, calendarEvent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, category, date, time, name, description, deleted);
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", category='" + getCategory() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

}
