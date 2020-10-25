package com.softhaxi.marves.core.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Component
public class AbsenceUtil {

    public boolean isSameDateAction(Date date1, Date date2) {
        Calendar first = Calendar.getInstance();
        first.setTime(date1);
        first.set(Calendar.HOUR, 0);
        first.set(Calendar.MINUTE, 0);
        first.set(Calendar.SECOND, 0);
        first.set(Calendar.MILLISECOND, 0);

        Calendar second = Calendar.getInstance();
        second.setTime(date2);
        second.set(Calendar.HOUR, 0);
        second.set(Calendar.MINUTE, 0);
        second.set(Calendar.SECOND, 0);
        second.set(Calendar.MILLISECOND, 0);

        return first.equals(second);
    }
}
