package com.softhaxi.marves.core.repository.attendence;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendence.DailyAttendence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */

public interface DailyAttendenceRepository extends JpaRepository<DailyAttendence, UUID> {
    public Optional<DailyAttendence> findFirstByOrderByDateTimeDesc();
    public Optional<DailyAttendence> findFirstByUserOrderByDateTimeDesc(User user);

    @Query("SELECT CAST(a.dateTime as date), a.workFrom, COUNT(a.workFrom) " +
        " FROM DailyAttendence a WHERE a.dateTime BETWEEN ?1 AND ?2" + 
        " GROUP BY CAST(a.dateTime as date), a.workFrom ORDER BY CAST(a.dateTime as date)")
    public Collection<Object[]> findStatisticWorkFromRangeDate(ZonedDateTime from, ZonedDateTime to);
}
