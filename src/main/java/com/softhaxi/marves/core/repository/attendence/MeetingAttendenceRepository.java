package com.softhaxi.marves.core.repository.attendence;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendence.MeetingAttendence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raka Sihombing
 * @since 1
 */
public interface MeetingAttendenceRepository extends JpaRepository<MeetingAttendence, UUID> {
    public Optional<MeetingAttendence> findFirstByUserAndCode(User user, String code);
}
