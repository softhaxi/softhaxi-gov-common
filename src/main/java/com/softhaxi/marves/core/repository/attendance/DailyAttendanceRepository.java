package com.softhaxi.marves.core.repository.attendance;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendance.DailyAttendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */

public interface DailyAttendanceRepository extends JpaRepository<DailyAttendance, UUID> {
    public Optional<DailyAttendance> findFirstByOrderByDateTimeDesc();
    public Optional<DailyAttendance> findFirstByUserOrderByDateTimeDesc(User user);

    @Query("SELECT CAST(a.dateTime as date), a.workFrom, COUNT(a.workFrom) " +
        " FROM DailyAttendance a WHERE a.dateTime BETWEEN ?1 AND ?2" + 
        " GROUP BY CAST(a.dateTime as date), a.workFrom ORDER BY CAST(a.dateTime as date)")
    public Collection<Object[]> findStatisticWorkFromRangeDate(ZonedDateTime from, ZonedDateTime to);

    @Query("FROM DailyAttendance WHERE user = ?1 AND dateTime >= ?2 AND dateTime < ?3 ORDER BY dateTime DESC")
    public Collection<DailyAttendance> findHistoryByUser(User user, ZonedDateTime from, ZonedDateTime to);
}
