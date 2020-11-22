package com.softhaxi.marves.core.service.support;

import java.util.Collection;

import com.softhaxi.marves.core.domain.support.Ticket;
import com.softhaxi.marves.core.repository.support.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    public Page<Ticket> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return ticketRepo.findAll(pageable);
    }

    public Collection<Ticket> findAllOrderByDateTimeDesc() {
        return ticketRepo.findAllOrderByDateTimeDesc();
    }

    public void performAction(Ticket ticket, String action) {
        ticket = ticketRepo.findById(ticket.getId()).orElse(null);

        if(ticket != null) {
            ticket.setStatus(action.toLowerCase().trim());
            ticketRepo.save(ticket);
        }
    }
}
