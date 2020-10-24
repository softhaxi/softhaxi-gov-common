package com.softhaxi.marves.core.repository.master;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.master.SystemParameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface SystemParameterRepository extends JpaRepository<SystemParameter, UUID> {
    @Query("FROM User WHERE UPPER(username) = UPPER(?1)")
    public Optional<SystemParameter> findByCode(String code);
}
