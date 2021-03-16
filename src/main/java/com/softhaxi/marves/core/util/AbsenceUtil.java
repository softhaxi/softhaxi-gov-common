package com.softhaxi.marves.core.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Component
public class AbsenceUtil {

    @Autowired
    private CalendarUtil calendarUtil;

    public boolean isSameDateAction(ZonedDateTime zonedDate1, ZonedDateTime zonedDate2) {
        LocalDate first = zonedDate1.toLocalDate();
        LocalDate second = zonedDate2.toLocalDate();

        return first.equals(second);
    }

    public Map<?, ?> calculateAbsenceTime(Map<?, ?> parameters, ZonedDateTime clockInTime, ZonedDateTime clockOutTime) {
        if (clockInTime == null && clockOutTime == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        long clockInBuffer = Long.parseLong(parameters.get("CLOCKIN_BUFFER_TIME").toString());
        ZonedDateTime paramClockIn = null;
        ZonedDateTime paramClockOut = null;
        String[] paramClockInArray = null, paramClockOutArray = null;
        clockInTime = clockInTime.withNano(0);
        if (clockOutTime != null)
            clockOutTime = clockOutTime.withNano(0);
        if (!clockInTime.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            paramClockInArray = parameters.get("CLOCKIN_MAX").toString().split(":");
            if (clockOutTime != null) {
                paramClockOutArray = parameters.get("CLOCKOUT_MAX").toString().split(":");
            }
        } else {
            paramClockInArray = parameters.get("CLOCKIN_MAX_FRIDAY").toString().split(":");
            if (clockOutTime != null) {
                paramClockOutArray = parameters.get("CLOCKOUT_MAX_FRIDAY").toString().split(":");
            }
        }

        paramClockIn = ZonedDateTime.of(clockInTime.getYear(), clockInTime.getMonthValue(), clockInTime.getDayOfMonth(),
                Integer.parseInt(paramClockInArray[0]), Integer.parseInt(paramClockInArray[1]),
                Integer.parseInt(paramClockInArray[2]), 0, clockInTime.getZone());
        if (clockOutTime != null) {
            paramClockOut = ZonedDateTime.of(clockOutTime.getYear(), clockOutTime.getMonthValue(),
                    clockOutTime.getDayOfMonth(), Integer.parseInt(paramClockOutArray[0]),
                    Integer.parseInt(paramClockOutArray[1]), Integer.parseInt(paramClockOutArray[2]), 0,
                    clockOutTime.getZone());
        }

        Duration minWorkDuration = Duration.ofHours(8).plusMinutes(30);
        if (clockInTime.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            minWorkDuration = Duration.ofHours(9);
        }

        if (!calendarUtil.isWeekend(clockInTime.toLocalDate())) {
            if(clockOutTime != null) {
                if (clockInTime.isBefore(paramClockIn) || clockInTime.isEqual(paramClockIn)) {
                    if (paramClockOut != null) {
                        if (clockOutTime.isBefore(paramClockOut)) {
                            result.put("early", Duration.between(clockOutTime, paramClockOut));
                        }
                    }
                } else if (clockInTime.isAfter(paramClockIn)) {
                    Duration duration = calculateWorkDuration(clockInTime, clockOutTime, false);
                    ZonedDateTime bufferClockIn = paramClockIn.plusMinutes(clockInBuffer);
                    if(clockInTime.isAfter(bufferClockIn)) {
                        result.put("late", Duration.between(bufferClockIn, clockInTime));
                    }
                    ZonedDateTime shouldClockOut = clockInTime.plusSeconds(minWorkDuration.getSeconds());
                    if (duration != null) {
                        if (duration.compareTo(minWorkDuration) < 0) {
                            result.put("early", Duration.between(clockOutTime, shouldClockOut));
                        }
                    }
                }
            } else {
                if (clockInTime.isAfter(paramClockIn)) {
                    ZonedDateTime bufferClockIn = paramClockIn.plusMinutes(clockInBuffer);
                    if(clockInTime.isAfter(bufferClockIn)) {
                        result.put("late", Duration.between(bufferClockIn, clockInTime));
                    }
                }
                if(!clockInTime.toLocalDate().equals(LocalDate.now())) {
                    result.put("notAbsence", true);
                } else {
                    result.put("outExpected", clockInTime.plusSeconds(minWorkDuration.getSeconds()));
                }
            }
        } else {
            if(clockInTime.toLocalDate().equals(LocalDate.now())) {
                result.put("outExpected", clockInTime.plusSeconds(minWorkDuration.getSeconds()));
            }
        }
        result.put("working", calculateWorkDuration(clockInTime, clockOutTime, true));
        return result;
    }

    public Duration calculateWorkDuration(ZonedDateTime clockInTime, ZonedDateTime clockOutTime, boolean today) {
        LocalTime startTime = null;
        LocalTime endTime = null;
        if (clockInTime != null) {
            startTime = LocalTime.ofInstant(clockInTime.toInstant(), ZoneOffset.systemDefault());
        }

        if (startTime != null) {

            if (clockOutTime != null) {
                endTime = LocalTime.ofInstant(clockOutTime.toInstant(), ZoneOffset.systemDefault());
            } else {
                if (!clockInTime.toLocalDate().equals(LocalDate.now())) {
                    return null;
                }
                if (today)
                    endTime = LocalTime.now();
            }
            if (endTime != null) {
                Duration duration = Duration.between(startTime, endTime);
                return duration;
            }
        }
        return null;
    }
}
