package com.softhaxi.marves.core.repository.logging;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.logging.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Query("FROM Session WHERE user = ?1 AND status='VALID'")
    public Collection<Session> findAllValidByUser(User user);

    @Query("FROM Session WHERE accessToken = ?1 AND Status='VALID'")
    public Optional<Session> findOneValidByAccessToken(String accessToken);

    @Query("FROM Session WHERE UPPER(status) = UPPER(?1) ORDER BY updatedAt DESC")
    public Collection<Session> findAllByStatus(String status);

    @Query("FROM Session WHERE UPPER(status) = UPPER(?1) AND lastUsed >= ?2 AND lastUsed <= ?3 ORDER BY updatedAt DESC")
    public Collection<Session> findAllByStatusAndDateRange(String status, ZonedDateTime start, ZonedDateTime end);
    
    @Query("SELECT COUNT(*) FROM Session WHERE UPPER(status) = UPPER(?1) AND lastUsed >= ?2 AND lastUsed <= ?3")
    public Object findStatisticStatusSession(String status, ZonedDateTime start, ZonedDateTime end);
}
