package com.softhaxi.marves.core.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softhaxi.marves.core.domain.attendence.DailyAttendence;
import com.softhaxi.marves.core.repository.attendence.DailyAttendenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ChartUtil
 */
@Service
public class ChartUtil {

    @Autowired
    private static DailyAttendenceRepository dailyAttendenceRepository;

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

        List<DailyAttendence> dailyAttendences = dailyAttendenceRepository.findAll();

        List<DailyAttendence> mondayAttendanceWFH = new ArrayList<>();
        List<DailyAttendence> mondayAttendanceWFO = new ArrayList<>();
        List<DailyAttendence> tuesdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendence> tuesdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendence> wednesdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendence> wednesdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendence> thursdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendence> thursdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendence> fridayAttendanceWFH = new ArrayList<>();
        List<DailyAttendence> fridayAttendanceWFO = new ArrayList<>();
        List<DailyAttendence> saturdayAttendanceWFH = new ArrayList<>();
        List<DailyAttendence> saturdayAttendanceWFO = new ArrayList<>();
        List<DailyAttendence> sundayAttendanceWFH = new ArrayList<>();
        List<DailyAttendence> sundayAttendanceWFO = new ArrayList<>();

        for (DailyAttendence dailyAttendence : dailyAttendences) {
            String day = new SimpleDateFormat("EEE").format(Date.from(dailyAttendence.getDateTime().toInstant()));
            if ("Mon".equals(day)) {
                if ("WFH".equals(dailyAttendence.getWorkFrom())) {
                    mondayAttendanceWFH.add(dailyAttendence);
                } else {
                    mondayAttendanceWFO.add(dailyAttendence);
                }
            } else if ("Tue".equals(day)) {
                if ("WFH".equals(dailyAttendence.getWorkFrom())) {
                    tuesdayAttendanceWFH.add(dailyAttendence);
                } else {
                    tuesdayAttendanceWFO.add(dailyAttendence);
                }

            } else if ("Wed".equals(day)) {
                if ("WFH".equals(dailyAttendence.getWorkFrom())) {
                    wednesdayAttendanceWFH.add(dailyAttendence);
                } else {
                    wednesdayAttendanceWFO.add(dailyAttendence);
                }

            } else if ("Thu".equals(day)) {
                if ("WFH".equals(dailyAttendence.getWorkFrom())) {
                    thursdayAttendanceWFH.add(dailyAttendence);
                } else {
                    thursdayAttendanceWFO.add(dailyAttendence);
                }

            } else if ("Fri".equals(day)) {
                if ("WFH".equals(dailyAttendence.getWorkFrom())) {
                    fridayAttendanceWFH.add(dailyAttendence);
                } else {
                    fridayAttendanceWFO.add(dailyAttendence);
                }

            } else if ("Sat".equals(day)) {
                if ("WFH".equals(dailyAttendence.getWorkFrom())) {
                    saturdayAttendanceWFH.add(dailyAttendence);
                } else {
                    saturdayAttendanceWFO.add(dailyAttendence);
                }

            } else {
                if ("WFH".equals(dailyAttendence.getWorkFrom())) {
                    mondayAttendanceWFH.add(dailyAttendence);
                } else {
                    mondayAttendanceWFO.add(dailyAttendence);
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