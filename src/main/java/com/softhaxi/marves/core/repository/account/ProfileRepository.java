package com.softhaxi.marves.core.repository.account;

import java.util.UUID;

import com.softhaxi.marves.core.domain.account.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andryan Situngkir
 * @since 1
 */
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    
}
