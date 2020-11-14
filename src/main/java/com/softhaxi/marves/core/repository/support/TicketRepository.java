package com.softhaxi.marves.core.repository.support;

import java.util.Collection;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.support.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    public Collection<Ticket> findAllByUserOrderByCreatedAt(User user);
    public Collection<Ticket> findAllByUserAndStatusOrderByCreatedAt(User user, String status);
}
