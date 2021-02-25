package com.softhaxi.marves.core.repository.logging;

import java.util.Collection;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.logging.LocationLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * LocationLogRepository
 */
public interface LocationLogRepository extends JpaRepository<LocationLog, String> {
    @Query("SELECT a FROM LocationLog a " +
        "WHERE a.dateTime=(SELECT MAX(b.dateTime) FROM LocationLog b WHERE b.user=a.user) " +
        "AND NOT EXISTS (SELECT c FROM LocationLog c WHERE c.user=a.user AND c.dateTime > a.dateTime)")
    public Collection<LocationLog> findAllLatestUpdated();

    @Query("SELECT a FROM LocationLog a " +
        "WHERE a.user = ?1 AND a.dateTime=(SELECT MAX(b.dateTime) FROM LocationLog b WHERE b.user=a.user AND b.user = ?1) " +
        "AND NOT EXISTS (SELECT c FROM LocationLog c WHERE c.user=a.user AND c.user = ?1 AND c.dateTime > a.dateTime)")
    public Collection<LocationLog> findAllLatestUpdatedByUser(User user);
}