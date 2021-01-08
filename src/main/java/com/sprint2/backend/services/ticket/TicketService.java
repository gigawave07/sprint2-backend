package com.sprint2.backend.services.ticket;

import java.util.List;

import com.sprint2.backend.entity.Ticket;
import com.sprint2.backend.model.TicketDTO;


public interface TicketService {
    List<Ticket> findAll();

    Ticket findByID(Long id);

    Ticket convert(TicketDTO ticketDTO);

    String save(TicketDTO ticketDTO);

    Boolean close(TicketDTO ticketDTO);
}
