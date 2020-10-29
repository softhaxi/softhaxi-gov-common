package com.softhaxi.marves.core.repository.messaging;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.messaging.Letter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface LetterRepository extends JpaRepository<Letter, UUID> {

    public Collection<Letter> findAllByUserOrderByReceivedAtDesc(User user);

    @Query("SELECT COUNT(*) FROM Letter WHERE user = ?1 AND isRead = false GROUP BY user")
    public Optional<Object[]> countUnreadByUser(User user);
}
