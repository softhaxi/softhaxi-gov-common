package com.softhaxi.marves.core.util;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Component
public class AbsenceUtil {

    public boolean isSameDateAction(ZonedDateTime zonedDate1, ZonedDateTime zonedDate2) {
        LocalDate first = zonedDate1.toLocalDate();
        LocalDate second = zonedDate2.toLocalDate();
        
        return first.equals(second);
    }
}
