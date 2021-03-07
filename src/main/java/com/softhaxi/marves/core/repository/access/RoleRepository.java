package com.softhaxi.marves.core.repository.access;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.access.Role;
import com.softhaxi.marves.core.domain.account.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("FROM Role WHERE LOWER(name) = LOWER(?1)")
    public Optional<Role> findByName(String name);

    @Query("FROM Role WHERE LOWER(name) != 'sadmin' AND id NOT IN (?1)")
    public Collection<Role> findAllNonSuperAdminExcept(Collection<UUID> ids);

    @Query("FROM Role WHERE LOWER(name) != 'sadmin' AND id NOT IN (?1) OR name NOT IN (?2)")
    public Collection<Role> findAllNonSuperAdminExcept(Collection<UUID> ids, Collection<String> names);

    @Query("FROM Role WHERE id IN (SELECT role.id FROM UserRole WHERE user = ?1)")
    public Collection<Role> findAllByUser(User user);
}
