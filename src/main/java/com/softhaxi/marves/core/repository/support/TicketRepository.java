package com.softhaxi.marves.core.repository.support;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.support.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @Query("FROM Ticket ORDER BY createdAt DESC")
    public Collection<Ticket> findAllOrderByDateTimeDesc();
    public Collection<Ticket> findAllByUserOrderByCreatedAt(User user);
    public Collection<Ticket> findAllByUserAndStatusOrderByCreatedAt(User user, String status);

    @Query("FROM Ticket where code = ?1")
    public Optional<Ticket> findTicketByCode(String strTicketCode);

    @Modifying
    @Transactional
    @Query("UPDATE Ticket set status = ?2 where code = ?1")
    public void updateTicketStatus(String ticketCode, String status);

    @Query("FROM Ticket where lower(code) like lower(concat('%', ?1, '%'))")
    public Collection<Ticket> findTicketLikeCode(String strTicketCode);

    @Query("FROM Ticket WHERE LOWER(status) != 'closed' ORDER BY createdAt")
    public Collection<Ticket> findAllNonClosed();

    @Query("FROM Ticket WHERE LOWER(status) = 'closed' ORDER BY updatedAt DESC")
    public Collection<Ticket> findAllClosed();

    @Query("FROM Ticket WHERE user = ?1 AND LOWER(status) != 'closed' ORDER BY createdAt")
    public Collection<Ticket> findAllNonClosedByUser(User user);

    @Query("FROM Ticket WHERE user = ?1 AND LOWER(status) = 'closed' ORDER BY updatedAt DESC")
    public Collection<Ticket> findAllClosedByUser(User user);

    @Query("FROM Ticket WHERE user = ?1 AND id = ?2")
    public Optional<Ticket> findOneByUserAndId(User user, UUID id);
}
