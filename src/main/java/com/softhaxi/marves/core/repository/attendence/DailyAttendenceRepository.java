package com.softhaxi.marves.core.repository.attendence;

import java.util.UUID;

import com.softhaxi.marves.core.domain.attendence.DailyAttendence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface DailyAttendenceRepository extends JpaRepository<DailyAttendence, UUID> {
    
}
