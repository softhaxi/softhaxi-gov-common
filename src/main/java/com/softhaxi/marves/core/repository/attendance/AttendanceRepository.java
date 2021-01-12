package com.softhaxi.marves.core.repository.attendance;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendance.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    public Optional<Attendance> getByUserAndId(User user, UUID id);
}
