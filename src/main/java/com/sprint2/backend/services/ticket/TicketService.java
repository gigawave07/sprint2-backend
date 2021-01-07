package com.sprint2.backend.services.ticket;

import com.sprint2.backend.entity.Ticket;

import java.util.List;


public interface TicketService {
    List<Ticket> findAll();

    Ticket findByID(Long id);


    /**
     * Nguyen Quang Danh
     *
     * @param fromEnterDay
     * @param toExitDay
     * @return Thống kê doanh thu trong khoảng thời gian (ticket)
     */
    Object getTotalRevenueTicketPeriod(String fromEnterDay, String toExitDay);
}
