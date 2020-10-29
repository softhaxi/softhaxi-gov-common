package com.softhaxi.marves.core.service.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendence.Attendence;
import com.softhaxi.marves.core.domain.attendence.DailyAttendence;
import com.softhaxi.marves.core.domain.attendence.MeetingAttendence;
import com.softhaxi.marves.core.domain.logging.ActivityLog;
import com.softhaxi.marves.core.domain.logging.LocationLog;
import com.softhaxi.marves.core.domain.master.Office;
import com.softhaxi.marves.core.repository.attendence.DailyAttendenceRepository;
import com.softhaxi.marves.core.repository.attendence.MeetingAttendenceRepository;
import com.softhaxi.marves.core.repository.master.OfficeRepository;
import com.softhaxi.marves.core.repository.master.SystemParameterRepository;
import com.softhaxi.marves.core.service.logging.LoggerService;
import com.softhaxi.marves.core.util.LocationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Service
public class AbsenceService {

    private static final Logger logger = LoggerFactory.getLogger(AbsenceService.class);

    @Autowired
    private DailyAttendenceRepository dailyAttendenceRepository;

    @Autowired
    private MeetingAttendenceRepository meetingRepository;

    @Autowired
    private SystemParameterRepository sysParamRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private LocationUtil locationUtil;

    public List<?> getDailyAbsenceCountWeekly() {
        LocalDate now = LocalDate.now();
        final LocalDate from = now.with(DayOfWeek.MONDAY).minusDays(1);
        final LocalDate to = now.with(DayOfWeek.SUNDAY);
        final long days = from.until(to, ChronoUnit.DAYS);
        logger.debug("[getDailyAbsenceCountWeekly] First day of week ..." + from);
        logger.debug("[getDailyAbsenceCountWeekly] Last day of week ..." + to);
        List<Integer> wfo = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        List<Integer> wfh = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        List<LocalDate> dateRange = Stream.iterate(from, 
            d -> d.plusDays(1))
            .limit(days)
            .collect(Collectors.toList());
        Collection<Object[]> data = dailyAttendenceRepository.findStatisticWorkFromRangeDate(
            now.with(DayOfWeek.MONDAY).minusDays(1).atStartOfDay(ZoneId.systemDefault()), 
            now.with(DayOfWeek.SATURDAY).atStartOfDay(ZoneId.systemDefault()));
        logger.debug("[getDailyAbsenceCountWeekly] Date Range size... " + dateRange.size());
        for(var record : data) {
            LocalDate date = LocalDate.parse(record[0].toString()); //LocalDate.ofInstant(record[0].toInstant(), ZoneId.systemDefault());
            var index = dateRange.indexOf(date);
            logger.debug("[getDailyAbsenceCountWeekly] Index..." + index);
            if(record[1].toString().equalsIgnoreCase("wfo")) {
                wfo.set(index, Integer.parseInt(record[2].toString()));
            } else {
                wfh.set(index, Integer.parseInt(record[2].toString()));
            }
            logger.debug("[getDailyAbsenceCountWeekly] date..." + dateRange.toString());
            logger.debug("[getDailyAbsenceCountWeekly] wfo..." + wfo.toString());
            logger.debug("[getDailyAbsenceCountWeekly] wfh..." + wfh.toString());
        }
        return Arrays.asList(dateRange, wfo, wfh);
    }

    public Attendence getLastAbsence(User user, String type, String code) {
        try {
            Attendence attendence = null;
            if(type.equalsIgnoreCase("DAILY")) {
                attendence = dailyAttendenceRepository.findFirstByUserOrderByDateTimeDesc(user)
                    .orElse(new DailyAttendence());
            } else if(type.equalsIgnoreCase("MEETING")) {
                attendence = meetingRepository.findFirstByUserAndCodeOrderByDateTimeDesc(user, code).orElse(new MeetingAttendence());
            }
            return attendence;
        } catch(Exception ex) {
            logger.error("[getLastAbsence] Exception..." + ex.getMessage(), ex);
            throw ex;
        }
    }

    public Attendence save(Attendence attendence) {
        ActivityLog activityLog = null;
        LocationLog locationLog = null;
        if(attendence instanceof DailyAttendence) {
            double limitRadiusInM = Double.parseDouble(sysParamRepository.findByCode("WFO_RADIUS_LIMIT").get().getValue());
            Office office = officeRepository.findHeadOffice().get();
            DailyAttendence daily = (DailyAttendence) attendence;
            if(daily.getInWork() == null) {
                if(daily.getLatitude() == 0.0 && daily.getLongitude() == 0.0) {
                    daily.setWorkFrom("wfh");
                    daily.setInWork("wfh");
                } else {
                    double distance = locationUtil.calculateDistanceKM(office.getLatitude(), office.getLongitude(), 
                        daily.getLatitude(), daily.getLongitude()) * 1000;
                    daily.setWorkFrom(distance <= limitRadiusInM ? "wfo" : "wfh");
                    daily.setInWork(distance <= limitRadiusInM ? "wfo" : "wfh");
                }
                activityLog = new ActivityLog().user(daily.getUser())
                    // .actionDate(daily.getDateTime().toLocalDate())
                    .actionTime(daily.getDateTime())
                    .actionName("clock.in")
                    .description(daily.getInWork());
                locationLog = new LocationLog().user(daily.getUser())
                    .dateTime(daily.getDateTime())
                    .latitude(daily.getLatitude())
                    .longitude(daily.getLongitude())
                    .isMockLocation(daily.isMockLocation());
                
            } else {
                if(daily.getOutLatitude() == 0.0 && daily.getOutLongitude() == 0.0) {
                    daily.setOutWork("wfh");
                } else {
                    double distance = locationUtil.calculateDistanceKM(office.getLatitude(), office.getLongitude(), 
                        daily.getOutLatitude(), daily.getOutLongitude()) * 1000;
                    daily.setOutWork(distance <= limitRadiusInM ? "wfo" : "wfh");
                    if(daily.getInWork().equalsIgnoreCase("wfh")) {
                        daily.setWorkFrom(distance <= limitRadiusInM ? "wfo" : "wfh");
                    }
                }
                activityLog = new ActivityLog().user(daily.getUser())
                    // .actionDate(daily.getOutDateTime().toLocalDate())
                    .actionTime(daily.getOutDateTime())
                    .actionName("clock.out")
                    .description(daily.getOutWork());
                locationLog = new LocationLog().user(daily.getUser())
                    .dateTime(daily.getOutDateTime())
                    .latitude(daily.getOutLatitude())
                    .longitude(daily.getOutLongitude())
                    .isMockLocation(daily.isOutMockLocation());
            }
            attendence = dailyAttendenceRepository.save(daily);
        } else if(attendence instanceof MeetingAttendence) {
            MeetingAttendence meeting = (MeetingAttendence) attendence;
            activityLog = new ActivityLog().user(meeting.getUser())
                    // .actionDate(meeting.getDateTime().toLocalDate())
                    .actionTime(meeting.getDateTime())
                    .actionName("check.in")
                    .description(meeting.getCode());
            locationLog = new LocationLog().user(meeting.getUser())
                    .dateTime(meeting.getDateTime())
                    .latitude(meeting.getLatitude())
                    .longitude(meeting.getLongitude())
                    .isMockLocation(meeting.isMockLocation());
            attendence = meetingRepository.save(meeting);
        }
        if(activityLog != null) {
            activityLog.setUri("/attendence");
            activityLog.setDeepLink(String.format("core://marves.dev/attendence?id=%s", 
                attendence.getId().toString()));
            activityLog.setReferenceId(attendence.getId().toString());
            loggerService.saveAsyncActivityLog(activityLog);
        }   
        if(locationLog != null)
            loggerService.saveAsyncLocationLog(locationLog);

        return attendence;
    }
}
