package com.softhaxi.marves.core.repository.attendance;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendance.Dispensation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DispensationRepository extends JpaRepository<Dispensation, UUID> {
    public Collection<Dispensation> findAllByUserOrderByStartDateDesc(User user);
    
    @Query("FROM Dispensation " +
        " WHERE user = ?1 AND startDate <= ?2 AND endDate >= ?2")
    public Collection<Dispensation> findByUserAndDate(User user, LocalDate date);

    @Query("FROM Dispensation " +
        " WHERE user = ?1 AND startDate >= ?2 AND endDate <= ?3")
    public Collection<Dispensation> findByUserAndBetweenDates(User user, LocalDate start, LocalDate end);

    @Query("FROM Dispensation WHERE CURRENT_DATE >= startDate  AND CURRENT_DATE <= endDate")
    public List<Dispensation> findDispensationByRangeDate();
}
