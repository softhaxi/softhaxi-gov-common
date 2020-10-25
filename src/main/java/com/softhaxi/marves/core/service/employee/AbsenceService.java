package com.softhaxi.marves.core.service.employee;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendence.Attendence;
import com.softhaxi.marves.core.domain.attendence.DailyAttendence;
import com.softhaxi.marves.core.domain.logging.LocationLog;
import com.softhaxi.marves.core.domain.master.Office;
import com.softhaxi.marves.core.repository.LocationLogRepository;
import com.softhaxi.marves.core.repository.attendence.DailyAttendenceRepository;
import com.softhaxi.marves.core.repository.master.OfficeRepository;
import com.softhaxi.marves.core.repository.master.SystemParameterRepository;
import com.softhaxi.marves.core.util.LocationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    private SystemParameterRepository sysParamRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private LocationLogRepository locationLogRepository;

    @Autowired
    private LocationUtil locationUtil;

    public Attendence getLastAbsence(User user, String type) {
        Attendence attendence = null;
        if(type.equalsIgnoreCase("DAILY")) {
            attendence = dailyAttendenceRepository.findFirstByUserOrderByDateTimeDesc(user)
                .orElse(new DailyAttendence());
        }
        return attendence;
    }

    public Attendence save(Attendence attendence) {
        LocationLog locationLog = new LocationLog();
        locationLog.setUser(attendence.getUser());
        if(attendence instanceof DailyAttendence) {
            double limitRadiusInM = Double.parseDouble(sysParamRepository.findByCode("WFO_RADIUS_LIMIT").get().getValue());
            Office office = officeRepository.findHeadOffice().get();
            DailyAttendence daily = (DailyAttendence) attendence;
            if(daily.getInWork() == null) {
                if(daily.getLatitude() == 0.0 && daily.getLongitude() == 0.0) {
                    daily.setWorkFrom("WFH");
                    daily.setInWork("WFH");
                } else {
                    double distance = locationUtil.calculateDistanceKM(office.getLatitude(), office.getLongitude(), 
                        daily.getLatitude(), daily.getLongitude()) * 1000;
                    logger.debug("[AbsenceService][save] Distance clock in...." + distance);
                    daily.setWorkFrom(distance <= limitRadiusInM ? "WFO" : "WFH");
                    daily.setInWork(distance <= limitRadiusInM ? "WFO" : "WFH");
                }
                locationLog.setDateTime(daily.getDateTime());
                locationLog.setLatitude(daily.getLatitude());
                locationLog.setLongitude(daily.getLongitude());
                locationLog.setIsMockLocation(daily.getIsOutMockLocation());
            } else {
                if(daily.getOutLatitude() == 0.0 && daily.getOutLongitude() == 0.0) {
                    daily.setOutWork("WFH");
                } else {
                    double distance = locationUtil.calculateDistanceKM(office.getLatitude(), office.getLongitude(), 
                        daily.getOutLatitude(), daily.getOutLongitude()) * 1000;
                    logger.debug("[AbsenceService][save] Distance clock out...." + distance);
                    daily.setOutWork(distance <= limitRadiusInM ? "WFO" : "WFH");
                    if(daily.getInWork().equalsIgnoreCase("WFH")) {
                        daily.setWorkFrom(distance <= limitRadiusInM ? "WFO" : "WFH");
                    }
                }
                locationLog.setDateTime(daily.getOutDateTime());
                locationLog.setLatitude(daily.getOutLatitude());
                locationLog.setLongitude(daily.getOutLongitude());
                locationLog.setIsMockLocation(daily.getIsOutMockLocation());
            }
            attendence = dailyAttendenceRepository.save(daily);
        }
        saveLocationLog(locationLog);

        return attendence;
    }

    @Async
    private void saveLocationLog(LocationLog locationLog) {
        locationLogRepository.save(locationLog);
    }
}
