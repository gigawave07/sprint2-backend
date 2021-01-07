package com.sprint2.backend.services.ticket;

import com.sprint2.backend.entity.Ticket;
import com.sprint2.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
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


    /**
     * Nguyen Quang Danh
     *
     * @param fromEnterDay
     * @param toExitDay
     * @return Thống kê doanh thu trong khoảng thời gian (ticket)
     */
    @Override
    public Object getTotalRevenueTicketPeriod(String fromEnterDay, String toExitDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localFromEnterDay = LocalDate.parse(fromEnterDay, formatter);
        LocalDate localToExitDay = LocalDate.parse(toExitDay, formatter);
        return this.ticketRepository.getTotalRevenueTicketPeriod(localFromEnterDay, localToExitDay);
    }


}
