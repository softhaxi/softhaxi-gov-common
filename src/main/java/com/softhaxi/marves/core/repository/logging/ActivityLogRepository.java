package com.softhaxi.marves.core.repository.logging;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.logging.ActivityLog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
    public List<ActivityLog> findByUserAndActionDateOrderByActionTimeDesc(User user, LocalDate actionDate);
}
