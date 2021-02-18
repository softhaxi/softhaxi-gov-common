package com.softhaxi.marves.core.repository.attendance;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendance.DailyAttendance;
import com.softhaxi.marves.core.model.employee.Absence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */

public interface DailyAttendanceRepository extends JpaRepository<DailyAttendance, UUID> {
    public Optional<DailyAttendance> findFirstByOrderByDateTimeDesc();

    public Optional<DailyAttendance> findFirstByUserOrderByDateTimeDesc(User user);

    @Query("SELECT CAST(a.dateTime as date), a.workFrom, COUNT(a.workFrom) "
            + " FROM DailyAttendance a WHERE a.dateTime BETWEEN ?1 AND ?2"
            + " GROUP BY CAST(a.dateTime as date), a.workFrom ORDER BY CAST(a.dateTime as date)")
    public Collection<Object[]> findStatisticWorkFromRangeDate(ZonedDateTime from, ZonedDateTime to);

    @Query("FROM DailyAttendance WHERE user = ?1 AND dateTime >= ?2 AND dateTime < ?3 ORDER BY dateTime DESC")
    public Collection<DailyAttendance> findHistoryByUser(User user, ZonedDateTime from, ZonedDateTime to);

    @Query("select d from DailyAttendance d where user = ?1 and month(dateTime) = ?2 and year(dateTime) = ?3")
    public List<DailyAttendance> findUserHistoryByMonthYear(User user, int month, int year);

    @Query("select count(d) from DailyAttendance d where mockLocation=true")
    public List<DailyAttendance> getCountByFakeLocator();

    @Query("SELECT DISTINCT EXTRACT(YEAR FROM dateTime) FROM DailyAttendance ORDER BY EXTRACT(YEAR FROM dateTime) DESC")
    public Collection<Object[]> findAllYearsByUser(User user);

    @Query("SELECT COUNT(*) FROM DailyAttendance WHERE dateTime IS NOT NULL AND dateTime >= ?1 AND dateTime <= ?2")
    public Object findStatisticClockInByDate(ZonedDateTime from, ZonedDateTime to);

    @Query("SELECT COUNT(*) FROM DailyAttendance WHERE outDateTime IS NOT NULL AND outDateTime >= ?1 AND outDateTime <= ?2")
    public Object findStatisticClockOutByDate(ZonedDateTime from, ZonedDateTime to);

    @Query("SELECT COUNT(*) FROM DailyAttendance "
            + "WHERE ((dateTime IS NOT NULL AND dateTime >= ?1 AND dateTime <= ?2) "
            + "OR (outDateTime IS NOT NULL AND outDateTime >= ?1 AND outDateTime <= ?2)) "
            + "AND (isMockLocation=true OR isOutMockLocation=true)")
    public Object findStatisticFakeLocatorByDate(ZonedDateTime from, ZonedDateTime to);

    @Query("FROM DailyAttendance " + "WHERE (dateTime IS NOT NULL AND dateTime >= ?1 AND dateTime <= ?2) "
            + "OR (outDateTime IS NOT NULL AND outDateTime >= ?1 AND outDateTime <= ?2) ORDER BY dateTime, outDateTime DESC")
    public Collection<DailyAttendance> findAllByDate(ZonedDateTime from, ZonedDateTime to);

    @Query("SELECT a FROM DailyAttendance a JOIN User b ON b.id=a.user.id "
            + "WHERE b.email IN (?1) AND ((a.dateTime IS NOT NULL AND a.dateTime >= ?2 AND a.dateTime <= ?3) "
            + "OR (a.outDateTime IS NOT NULL AND a.outDateTime >= ?2 AND a.outDateTime <= ?3)) ORDER BY a.dateTime, a.outDateTime DESC")
    public Collection<DailyAttendance> findAllByEmailsAndDate(Collection<String> emails, ZonedDateTime from,
            ZonedDateTime to);
}
