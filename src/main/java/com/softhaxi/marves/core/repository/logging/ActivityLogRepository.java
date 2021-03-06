package com.softhaxi.marves.core.repository.logging;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
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
    public default Collection<ActivityLog> findAllUserActivity(User user, LocalDate actionDate) {
        return findAllUserDailyActivityOrderByActionTimeDesc(user, actionDate.atStartOfDay(ZoneId.systemDefault()),
                actionDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()));
    }

    @Query("FROM ActivityLog WHERE user = ?1 AND actionType <> 'audit' AND actionTime >= ?2 AND actionTime <= ?3 ORDER BY actionTime DESC")
    Collection<ActivityLog> findAllUserDailyActivityOrderByActionTimeDesc(User user, ZonedDateTime startTime,
            ZonedDateTime endTime);

    @Query("FROM ActivityLog WHERE user = ?1 and lower(actionName) like lower(concat('%', ?2,'%')) ORDER BY actionTime DESC")
    List<ActivityLog> findActivityLogByUserName(User user, String actionName);

    @Query("FROM ActivityLog WHERE lower(actionName) like lower(concat('%', ?1,'%')) ORDER BY actionTime DESC")
    List<ActivityLog> findActivityLogByAction(String actionName);

    @Query("SELECT count(distinct a.user.id) FROM ActivityLog a WHERE a.actionName = ?1 ")
    Integer findUserByActionName(String actionName);

    public default Object findStatisticByActionAndDate(String actionName, LocalDate date) {
        return findStatisticByActionAndActionTime(actionName, date.atStartOfDay(ZoneId.systemDefault()),
                date.plusDays(1).atStartOfDay(ZoneId.systemDefault()));
    }

    @Query("SELECT COUNT(*) FROM ActivityLog WHERE actionName = ?1 AND actionTime >= ?2 AND actionTime <= ?3")
    Object findStatisticByActionAndActionTime(String actionName, ZonedDateTime startTime, ZonedDateTime endTime);

    @Query("FROM ActivityLog WHERE referenceId = ?1 ORDER BY actionTime DESC")
    public Collection<ActivityLog> findAllByRefIdOrderByActionTimeDesc(String referenceId);

}
