package com.softhaxi.marves.core.repository.logging;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.logging.ActivityLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
    public default Collection<ActivityLog> findByUserAndActionDateOrderByActionTimeDesc(User user, LocalDate actionDate) {
        return findByUserAndActionTimeBetweenOrderByActionTimeDesc(user, 
            actionDate.atStartOfDay(ZoneId.systemDefault()), 
            actionDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()));
    }

    @Query(value = "FROM ActivityLog WHERE user = ?1 AND actionTime >= ?2 AND actionTime <= ?3 ORDER BY actionTime DESC")
    Collection<ActivityLog> findByUserAndActionTimeBetweenOrderByActionTimeDesc(User user, ZonedDateTime startTime, ZonedDateTime endTime);
}
