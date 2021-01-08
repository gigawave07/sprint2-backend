package com.sprint2.backend.services.ticket;

import com.sprint2.backend.entity.Ticket;
import com.sprint2.backend.model.TicketDTO;

import java.util.List;


public interface TicketService {
    List<Ticket> findAll();

    Ticket findByID(Long id);

    Ticket convert(TicketDTO ticketDTO);

    String save(TicketDTO ticketDTO);

    Boolean close(TicketDTO ticketDTO);


    /**
     * Nguyen Quang Danh
     *
     * @param fromEnterDay
     * @param toExitDay
     * @return Thống kê doanh thu trong khoảng thời gian (ticket)
     */
    Object getTotalRevenueTicketPeriod(String fromEnterDay, String toExitDay);
}
