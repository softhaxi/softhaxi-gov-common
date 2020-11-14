package com.softhaxi.marves.core.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class CalendarUtil {
    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() == 7 || date.getDayOfWeek().getValue() == 6;
    }
}
