package com.softhaxi.marves.core.repository.logging;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.logging.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Query("FROM Session where user = ?1 AND status='VALID'")
    public Collection<Session> findAllValidByUser(User user);

    @Query("SELECT COUNT(*) FROM Session WHERE status='VALID'")
    public Object findStatisticValidSession();
}