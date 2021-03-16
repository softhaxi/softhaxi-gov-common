package com.softhaxi.marves.core.repository.master;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.master.CalendarEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, UUID> {
    @Query("FROM CalendarEvent WHERE EXTRACT(YEAR FROM date) = ?1 AND deleted = false")
    public Collection<CalendarEvent> findAllByYear(int year);

    @Query("FROM CalendarEvent WHERE date >= ?1 AND date <= ?2 AND type = 'holiday' AND deleted = false")
    public Collection<CalendarEvent> findAllByDateRange(LocalDate startDate, LocalDate endDate);

    @Query("FROM CalendarEvent WHERE date = ?1 AND type = 'holiday' AND deleted = false")
    public Optional<CalendarEvent> findOneHolidayByDate(LocalDate date);
}
