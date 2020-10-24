package com.softhaxi.marves.core.repository.access;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.access.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("FROM Role WHERE LOWER(name) = LOWER(?1)")
    public Optional<Role> findByName(String name);
}
