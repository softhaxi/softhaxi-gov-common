package com.softhaxi.marves.core.service.employee;

import com.softhaxi.marves.core.domain.attendence.Attendence;
import com.softhaxi.marves.core.domain.attendence.DailyAttendence;
import com.softhaxi.marves.core.domain.master.Office;
import com.softhaxi.marves.core.repository.attendence.DailyAttendenceRepository;
import com.softhaxi.marves.core.repository.master.OfficeRepository;
import com.softhaxi.marves.core.repository.master.SystemParameterRepository;
import com.softhaxi.marves.core.util.LocationUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Service
public class AbsenceService {

    @Autowired
    private DailyAttendenceRepository dailyAttendenceRepository;

    @Autowired
    private SystemParameterRepository sysParamRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private LocationUtil locationUtil;

    public Attendence getLastAbsence(String type) {
        Attendence attendence = null;
        if(type.equalsIgnoreCase("DAILY")) {
            attendence = dailyAttendenceRepository.findFirstByOrderByDateTimeDesc()
                .orElse(new DailyAttendence());
        }
        return attendence;
    }

    public Attendence save(Attendence attendence) {
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
                    daily.setWorkFrom(distance <= limitRadiusInM ? "WFO" : "WFH");
                    daily.setInWork(distance <= limitRadiusInM ? "WFO" : "WFH");
                }
            } else {
                if(daily.getOutLatitude() == 0.0 && daily.getOutLongitude() == 0.0) {
                    daily.setWorkFrom("WFH");
                    daily.setOutWork("WFH");
                } else {
                    double distance = locationUtil.calculateDistanceKM(office.getLatitude(), office.getLongitude(), 
                        daily.getOutLatitude(), daily.getOutLongitude()) * 1000;
                    daily.setWorkFrom(distance <= limitRadiusInM ? "WFO" : "WFH");
                    daily.setOutWork(distance <= limitRadiusInM ? "WFO" : "WFH");
                }
            }
            attendence = dailyAttendenceRepository.save(daily);
        }

        return attendence;
    }
}
