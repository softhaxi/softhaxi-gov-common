package com.softhaxi.marves.core.service.support;

import java.util.Collection;
import java.util.Optional;

import com.softhaxi.marves.core.domain.account.User;
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

    public Collection<Ticket> findByUserId(User user){
        return ticketRepo.findAllByUserOrderByCreatedAt(user);
    }

    public Optional<Ticket> findTicketByCode(String ticketCode){
        return ticketRepo.findTicketByCode(ticketCode);
    }

    public void updateTicketStatus(String ticketCode, String status){
        ticketRepo.updateTicketStatus(ticketCode, status);
    }

    public Collection<Ticket> findTicketLikeCode(String strTicketCode){
        return ticketRepo.findTicketLikeCode(strTicketCode);
    }
}
