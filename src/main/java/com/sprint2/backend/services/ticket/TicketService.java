package com.sprint2.backend.services.ticket;

import java.util.List;

import com.sprint2.backend.entity.Ticket;


public interface TicketService {
    List<Ticket> findAll();

    Ticket findByID(Long id);
}
