package com.softhaxi.marves.core.service.logging;

import com.softhaxi.marves.core.domain.logging.ActivityLog;
import com.softhaxi.marves.core.domain.logging.LocationLog;
import com.softhaxi.marves.core.repository.logging.ActivityLogRepository;
import com.softhaxi.marves.core.repository.logging.LocationLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Service
public class LoggerService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private LocationLogRepository locationLogRepository;

    @Async
    public void saveAsyncActivityLog(ActivityLog activityLog) {
        activityLogRepository.save(activityLog);
    }

    @Async
    public void saveAsyncLocationLog(LocationLog locationLog) {
        locationLogRepository.save(locationLog);
    }
}
