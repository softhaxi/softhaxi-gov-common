package com.softhaxi.marves.core.service.logging;

import java.util.ArrayList;
import java.util.List;

import com.softhaxi.marves.core.domain.logging.LocationLog;
import com.softhaxi.marves.core.repository.logging.LocationLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LocationLogService
 */

@Service
public class LocationLogService {

    @Autowired
    private LocationLogRepository locationLogRepository;

    public List<LocationLog> findAllLocationLog() {
        List<LocationLog> locationLogList = new ArrayList<>();
        locationLogRepository.findAll().forEach(locationLogList::add);

        return locationLogList;
    }
}