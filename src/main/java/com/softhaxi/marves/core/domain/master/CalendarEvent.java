package com.softhaxi.marves.core.domain.master;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.softhaxi.marves.core.domain.Auditable;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class CalendarEvent extends Auditable<String> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3240356752494705745L;
    protected UUID id;
    protected LocalDate date;
    protected ZonedDateTime dateTime;
    protected String desciption;
    protected boolean isHoliday;
    protected String holidayType;
}
