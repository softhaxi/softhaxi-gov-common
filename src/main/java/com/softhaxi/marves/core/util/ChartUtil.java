package com.softhaxi.marves.core.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softhaxi.marves.core.domain.attendance.DailyAttendance;
import com.softhaxi.marves.core.repository.attendance.DailyAttendanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ChartUtil
 */
@Service
public class ChartUtil {

    @Autowired
    private static DailyAttendanceRepository dailyAttendanceRepository;

    enum Days {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    public static List<List<Map<Object, Object>>> getCanvasjsDataList() {
        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();

        List<Map<Object, Object>> mondayWFHData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> mondayWFOData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> tuesdayWFHData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> tuesdayWFOData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> wednesdayWFHData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> wednesdayWFOData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> thursdayWFHData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> thursdayWFOData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> fridayWFHData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> fridayWFOData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> saturdayWFHData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> saturdayWFOData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> sundayWFHData = new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> sundayWFOData = new ArrayList<Map<Object, Object>>();

        List<DailyAttendance> dailyAttendances = dailyAttendanceRepository.findAll();

        List<DailyAttendance> mondayAttendanceWFH = new ArrayList<>();
        List<DailyAttendance> mondayAttendanceWFO = new ArrayList<>();
        List<DailyAttendance> tuesdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendance> tuesdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendance> wednesdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendance> wednesdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendance> thursdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendance> thursdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendance> fridayAttendanceWFH = new ArrayList<>();
        List<DailyAttendance> fridayAttendanceWFO = new ArrayList<>();
        List<DailyAttendance> saturdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendance> saturdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendance> sundayAttendanceWFH = new ArrayList<>();
        List<DailyAttendance> sundayAttendanceWFO = new ArrayList<>();

        for (DailyAttendance dailyAttendance : dailyAttendances) {
            String day = new SimpleDateFormat("EEE").format(Date.from(dailyAttendance.getDateTime().toInstant()));
            if ("Mon".equals(day)) {
                if ("WFH".equals(dailyAttendance.getWorkFrom())) {
                    mondayAttendanceWFH.add(dailyAttendance);
                } else {
                    mondayAttendanceWFO.add(dailyAttendance);
                }
            } else if ("Tue".equals(day)) {
                if ("WFH".equals(dailyAttendance.getWorkFrom())) {
                    tuesdayAttendanceWFH.add(dailyAttendance);
                } else {
                    tuesdayAttendanceWFO.add(dailyAttendance);
                }

            } else if ("Wed".equals(day)) {
                if ("WFH".equals(dailyAttendance.getWorkFrom())) {
                    wednesdayAttendanceWFH.add(dailyAttendance);
                } else {
                    wednesdayAttendanceWFO.add(dailyAttendance);
                }

            } else if ("Thu".equals(day)) {
                if ("WFH".equals(dailyAttendance.getWorkFrom())) {
                    thursdayAttendanceWFH.add(dailyAttendance);
                } else {
                    thursdayAttendanceWFO.add(dailyAttendance);
                }

            } else if ("Fri".equals(day)) {
                if ("WFH".equals(dailyAttendance.getWorkFrom())) {
                    fridayAttendanceWFH.add(dailyAttendance);
                } else {
                    fridayAttendanceWFO.add(dailyAttendance);
                }

            } else if ("Sat".equals(day)) {
                if ("WFH".equals(dailyAttendance.getWorkFrom())) {
                    saturdayAttendanceWFH.add(dailyAttendance);
                } else {
                    saturdayAttendanceWFO.add(dailyAttendance);
                }

            } else {
                if ("WFH".equals(dailyAttendance.getWorkFrom())) {
                    mondayAttendanceWFH.add(dailyAttendance);
                } else {
                    mondayAttendanceWFO.add(dailyAttendance);
                }

            }
        }

        map = new HashMap<Object, Object>();
        map.put("x", "Monday");
        map.put("y", mondayAttendanceWFH.size());
        mondayWFHData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Monday");
        map.put("y", mondayAttendanceWFO.size());
        mondayWFOData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Tuesday");
        map.put("y", tuesdayAttendanceWFH.size());
        tuesdayWFHData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Tuesday");
        map.put("y", tuesdayAttendanceWFO.size());
        tuesdayWFOData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Wednesday");
        map.put("y", wednesdayAttendanceWFH.size());
        wednesdayWFHData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Wednesday");
        map.put("y", wednesdayAttendanceWFO.size());
        wednesdayWFOData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Thursday");
        map.put("y", thursdayAttendanceWFH.size());
        thursdayWFHData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Thursday");
        map.put("y", thursdayAttendanceWFO.size());
        thursdayWFOData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Friday");
        map.put("y", fridayAttendanceWFH.size());
        fridayWFHData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Friday");
        map.put("y", fridayAttendanceWFO.size());
        fridayWFOData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Saturday");
        map.put("y", saturdayAttendanceWFH.size());
        saturdayWFHData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Saturday");
        map.put("y", saturdayAttendanceWFO.size());
        saturdayWFOData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Sunday");
        map.put("y", sundayAttendanceWFH.size());
        sundayWFHData.add(map);

        map = new HashMap<Object, Object>();
        map.put("x", "Sunday");
        map.put("y", sundayAttendanceWFO.size());
        sundayWFOData.add(map);

        list.add(mondayWFHData);
        list.add(mondayWFOData);
        list.add(tuesdayWFHData);
        list.add(tuesdayWFOData);
        list.add(wednesdayWFHData);
        list.add(wednesdayWFOData);
        list.add(thursdayWFHData);
        list.add(thursdayWFOData);
        list.add(fridayWFHData);
        list.add(fridayWFOData);
        list.add(saturdayWFHData);
        list.add(saturdayWFOData);
        list.add(sundayWFHData);
        list.add(sundayWFOData);

        return list;
    }
}