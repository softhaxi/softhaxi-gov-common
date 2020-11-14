package com.softhaxi.marves.core.repository.support;

import java.util.UUID;

import com.softhaxi.marves.core.domain.support.TicketComment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCommentRepository extends JpaRepository<TicketComment, UUID> {
    
}
