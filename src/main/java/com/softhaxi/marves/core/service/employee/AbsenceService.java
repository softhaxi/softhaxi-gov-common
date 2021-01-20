package com.softhaxi.marves.core.service.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendance.Attendance;
import com.softhaxi.marves.core.domain.attendance.DailyAttendance;
import com.softhaxi.marves.core.domain.attendance.MeetingAttendance;
import com.softhaxi.marves.core.domain.logging.ActivityLog;
import com.softhaxi.marves.core.domain.logging.LocationLog;
import com.softhaxi.marves.core.domain.master.Office;
import com.softhaxi.marves.core.repository.attendance.AttendanceRepository;
import com.softhaxi.marves.core.repository.attendance.DailyAttendanceRepository;
import com.softhaxi.marves.core.repository.attendance.MeetingAttendanceRepository;
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
    private AttendanceRepository attendenceRepo;

    @Autowired
    private DailyAttendanceRepository dailyAttendanceRepository;

    @Autowired
    private MeetingAttendanceRepository meetingRepository;

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
        final LocalDate from = now.with(DayOfWeek.MONDAY);
        final LocalDate to = now.with(DayOfWeek.SUNDAY).plusDays(1);
        final long days = from.until(to, ChronoUnit.DAYS);
        logger.debug("[getDailyAbsenceCountWeekly] First day of week ..." + from);
        logger.debug("[getDailyAbsenceCountWeekly] Last day of week ..." + to);
        List<Integer> wfo = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        List<Integer> wfh = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        List<LocalDate> dateRange = Stream.iterate(from, 
            d -> d.plusDays(1))
            .limit(days)
            .collect(Collectors.toList());
        Collection<Object[]> data = dailyAttendanceRepository.findStatisticWorkFromRangeDate(from.atStartOfDay(ZoneId.systemDefault()), 
            to.atStartOfDay(ZoneId.systemDefault()));
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

    public Attendance getLastAbsence(User user, String type, String code) {
        try {
            Attendance attendence = null;
            if(type.equalsIgnoreCase("DAILY")) {
                attendence = dailyAttendanceRepository.findFirstByUserOrderByDateTimeDesc(user)
                    .orElse(new DailyAttendance());
            } else if(type.equalsIgnoreCase("MEETING")) {
                attendence = meetingRepository.findFirstByUserAndCodeOrderByDateTimeDesc(user, code).orElse(new MeetingAttendance());
            }
            return attendence;
        } catch(Exception ex) {
            logger.error("[getLastAbsence] Exception..." + ex.getMessage(), ex);
            throw ex;
        }
    }

    public Attendance save(Attendance attendence) {
        ActivityLog activityLog = null;
        LocationLog locationLog = null;
        if(attendence instanceof DailyAttendance) {
            double limitRadiusInM = Double.parseDouble(sysParamRepository.findByCode("WFO_RADIUS_LIMIT").get().getValue());
            Office office = officeRepository.findHeadOffice().get();
            DailyAttendance daily = (DailyAttendance) attendence;
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
                    .actionTime(daily.getDateTime())
                    .actionName("clock.in")
                    .description(daily.getInWork())
                    .ipAddress(daily.getIpAddress());
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
                    .actionTime(daily.getOutDateTime())
                    .actionName("clock.out")
                    .description(daily.getWorkFrom())
                    .ipAddress(daily.getOutIpAddress());
                locationLog = new LocationLog().user(daily.getUser())
                    .dateTime(daily.getOutDateTime())
                    .latitude(daily.getOutLatitude())
                    .longitude(daily.getOutLongitude())
                    .isMockLocation(daily.isOutMockLocation());
            }
            attendence = dailyAttendanceRepository.save(daily);
        } else if(attendence instanceof MeetingAttendance) {
            MeetingAttendance meeting = (MeetingAttendance) attendence;
            activityLog = new ActivityLog().user(meeting.getUser())
                    .actionTime(meeting.getDateTime())
                    .actionName("check.in")
                    .description(meeting.getCode())
                    .ipAddress(meeting.getIpAddress());
            locationLog = new LocationLog().user(meeting.getUser())
                    .dateTime(meeting.getDateTime())
                    .latitude(meeting.getLatitude())
                    .longitude(meeting.getLongitude())
                    .isMockLocation(meeting.isMockLocation());
            attendence = meetingRepository.save(meeting);
        }
        if(activityLog != null) {
            activityLog.setUri("/attendence");
            activityLog.setDeepLink("core://marves.dev/attendence");
            activityLog.setReferenceId(attendence.getId().toString());
            activityLog.setActionType("daily");
            loggerService.saveAsyncActivityLog(activityLog);
        }   
        if(locationLog != null)
            loggerService.saveAsyncLocationLog(locationLog);

        return attendence;
    }

	public Attendance getByUserAndId(User user, UUID id) {
		return attendenceRepo.getByUserAndId(user, id).orElse(null);
    }
    
    public Collection<?> getHistoryByUser(User user, String type, ZonedDateTime from, ZonedDateTime to) {
        switch(type) {
            case "daily":
                return dailyAttendanceRepository.findHistoryByUser(user, from, to);
            default:
                return null;
        }
    }
}
