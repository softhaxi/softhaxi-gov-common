package com.softhaxi.marves.core.repository.account;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Andryan Situngkir
 * @since 1
 */
public interface UserRepository extends JpaRepository<User, UUID>{

    public Optional<User> findByUsername(String username);

    @Query("FROM User WHERE lower(username) = lower(?1) or lower(email)=lower(?1)")
    public Optional<User> findByUsernameOrEmailIgnoreCase(String username);
    
    @Query("FROM User WHERE username =?1 and password=?2")
    public Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("FROM User u left join UserRole ur on u.id = ur.user.id left join Role r on r.id = ur.role.id where r.name='MOBILE'")
    public Collection<User> findAllNonAdminUsers();
}