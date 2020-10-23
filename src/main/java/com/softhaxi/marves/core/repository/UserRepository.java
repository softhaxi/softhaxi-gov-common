package com.softhaxi.marves.core.repository;

import java.util.Optional;

import com.softhaxi.marves.core.domain.account.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 */
public interface UserRepository extends CrudRepository<User, String>{

    @Query("FROM User WHERE username = ?1")
    public Optional<User> getByUsername(String username);
    
    @Query("FROM User WHERE username =?1 and password=?2")
    public Optional<User> findUserByUsernameAndPassword(String username, String password);
}