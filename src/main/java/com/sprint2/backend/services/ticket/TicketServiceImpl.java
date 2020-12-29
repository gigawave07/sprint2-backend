package com.sprint2.backend.services.ticket;

import com.sprint2.backend.entity.Ticket;
import com.sprint2.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket findByID(Long id) {
        return this.ticketRepository.findById(id).orElse(null);
    }
}
