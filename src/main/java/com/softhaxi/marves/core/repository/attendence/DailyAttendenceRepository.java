package com.softhaxi.marves.core.repository.attendence;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendence.DailyAttendence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raja Sihombing
 * @since 1
 */

public interface DailyAttendenceRepository extends JpaRepository<DailyAttendence, UUID> {
    public Optional<DailyAttendence> findFirstByOrderByDateTimeDesc();
    public Optional<DailyAttendence> findFirstByUserOrderByDateTimeDesc(User user);
}
