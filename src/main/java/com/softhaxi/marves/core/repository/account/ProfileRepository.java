package com.softhaxi.marves.core.repository.account;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.Profile;
import com.softhaxi.marves.core.domain.account.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andryan Situngkir
 * @since 1
 */
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    public Optional<Profile> findByUser(User user);
}
