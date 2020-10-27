package com.softhaxi.marves.core.repository.employee;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.employee.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    //@Query(value = "SELECT a.* FROM schedules a WHERE DATE(start_time) >= ?1 AND DATE(end_time) <= ?1", nativeQuery = true)
    public default Collection<Schedule> findAllByUserAndDate(User user, LocalDate date) {
        return findAllByUserAndZonedDateTime(user, date.atStartOfDay(ZoneId.systemDefault()), date.plusDays(1).atStartOfDay(ZoneId.systemDefault()));
    }

    @Query(value = "FROM Schedule WHERE user = ?1 AND startTime >= ?2 AND endTime <= ?3")
    Collection<Schedule> findAllByUserAndZonedDateTime(User user, ZonedDateTime startTime, ZonedDateTime endTime);
}
