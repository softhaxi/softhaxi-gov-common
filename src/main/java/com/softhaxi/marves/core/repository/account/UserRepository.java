package com.softhaxi.marves.core.repository.account;

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

    @Query("FROM User WHERE LOWER(username) = LOWER(?1)")
    public Optional<User> findByUsername(String username);
    
    @Query("FROM User WHERE username =?1 and password=?2")
    public Optional<User> findUserByUsernameAndPassword(String username, String password);
}