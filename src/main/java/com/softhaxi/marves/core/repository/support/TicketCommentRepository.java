package com.softhaxi.marves.core.repository.support;

import java.util.Collection;
import java.util.UUID;

import com.softhaxi.marves.core.domain.support.Ticket;
import com.softhaxi.marves.core.domain.support.TicketComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketCommentRepository extends JpaRepository<TicketComment, UUID> {
    @Query("FROM TicketComment WHERE ticket = ?1 ORDER BY createdAt DESC")
    public Collection<TicketComment> findAllByTicketOrderByCreatedAtDesc(Ticket ticket);
}
