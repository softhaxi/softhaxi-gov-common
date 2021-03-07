package com.softhaxi.marves.core.repository.access;

import java.util.Collection;
import java.util.UUID;

import com.softhaxi.marves.core.domain.access.UserRole;
import com.softhaxi.marves.core.domain.account.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * @author Raja Sihombing
 * @since 1
 */
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    @Query("FROM UserRole WHERE user = ?1 ORDER BY createdAt")
    public Collection<UserRole> findByUser(User user);
}
