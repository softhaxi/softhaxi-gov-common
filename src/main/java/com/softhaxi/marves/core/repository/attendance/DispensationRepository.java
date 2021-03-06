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

    @Query("FROM Dispensation " + " WHERE user = ?1 AND startDate <= ?2 AND endDate >= ?2")
    public Collection<Dispensation> findByUserAndDate(User user, LocalDate date);

    @Query("FROM Dispensation " + " WHERE user = ?1 AND (startDate < ?2 OR endDate < ?2) ORDER BY startDate DESC")
    public Collection<Dispensation> findAllByUserAndBeforeDate(User user, LocalDate date);

    @Query("FROM Dispensation " + " WHERE user = ?1 AND startDate >= ?2 AND endDate <= ?3")
    public Collection<Dispensation> findByUserAndBetweenDates(User user, LocalDate start, LocalDate end);

    @Query("FROM Dispensation WHERE CURRENT_DATE >= startDate  AND CURRENT_DATE <= endDate")
    public List<Dispensation> findDispensationByRangeDate();

    @Query("FROM Dispensation WHERE startDate <= ?1 AND endDate >= ?1")
    public Collection<Dispensation> findAllByDate(LocalDate date);

    @Query("SELECT COUNT(*) FROM Dispensation WHERE startDate <= ?1 AND endDate >= ?1")
    public Object findStatisticByDate(LocalDate date);

    @Query("SELECT COUNT(*) FROM Dispensation WHERE type = ?1 AND startDate <= ?2 AND endDate >= ?2")
    public Object findStatisticByTypeAndDate(String type, LocalDate date);

    @Query("SELECT COUNT(*) FROM Dispensation WHERE user = ?1 AND type = ?2 AND (EXTRACT(YEAR FROM startDate) = ?3 OR EXTRACT(YEAR FROM endDate) = ?3)")
    public Object findStatisticByUserAndTypeAndYear(User user, String type, int year);

    @Query("SELECT startDate, endDate, COUNT(startDate) " + " FROM Dispensation WHERE startDate >= ?1 AND endDate <= ?2"
            + " GROUP BY startDate, endDate ORDER BY startDate")
    public Collection<Object[]> findStatisticFromRangeDate(LocalDate start, LocalDate end);

    @Query("SELECT a FROM Dispensation a JOIN User b ON b.id=a.user.id WHERE a.startDate <= ?1 AND a.endDate >= ?1 AND b.email IN (?2)")
    public Collection<Dispensation> findAllByDateAndEmails(LocalDate date, Collection<String> emails);

}
