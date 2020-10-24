package com.softhaxi.marves.core.repository.master;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.master.Office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface OfficeRepository extends JpaRepository<Office, UUID> {
    @Query("FROM Office WHERE type='HO'")
    public Optional<Office> findHeadOffice();
}
