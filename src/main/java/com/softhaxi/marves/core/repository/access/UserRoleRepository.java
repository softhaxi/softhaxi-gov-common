package com.softhaxi.marves.core.repository.access;

import java.util.UUID;

import com.softhaxi.marves.core.domain.access.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Raja Sihombing
 * @since 1
 */
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    
}
