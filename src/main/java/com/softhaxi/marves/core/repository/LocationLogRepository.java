package com.softhaxi.marves.core.repository;

import com.softhaxi.marves.core.domain.logging.LocationLog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LocationLogRepository
 */
public interface LocationLogRepository extends JpaRepository<LocationLog, String> {

    
}