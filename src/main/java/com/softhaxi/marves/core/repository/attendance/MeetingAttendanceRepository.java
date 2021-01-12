package com.softhaxi.marves.core.repository.attendance;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendance.MeetingAttendance;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface MeetingAttendanceRepository extends JpaRepository<MeetingAttendance, UUID> {
    public Optional<MeetingAttendance> findFirstByUserAndCode(User user, String code);
    public Optional<MeetingAttendance> findFirstByUserAndCodeOrderByDateTimeDesc(User user, String code);
}
