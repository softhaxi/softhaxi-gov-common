package com.softhaxi.marves.core.repository.logging;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.logging.ActivityLog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
    public default Collection<ActivityLog> findAllUserDailyActivity(User user, LocalDate actionDate) {
        return findAllUserDailyActivityOrderByActionTimeDesc(user, 
            actionDate.atStartOfDay(ZoneId.systemDefault()), 
            actionDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()));
    }

    @Query("FROM ActivityLog WHERE user = ?1 AND actionType='daily' AND actionTime >= ?2 AND actionTime <= ?3 ORDER BY actionTime DESC")
    Collection<ActivityLog> findAllUserDailyActivityOrderByActionTimeDesc(User user, ZonedDateTime startTime, ZonedDateTime endTime);

    @Query("FROM ActivityLog WHERE user = ?1")
    List<ActivityLog> findActivityLogByUserName(User user);
}
