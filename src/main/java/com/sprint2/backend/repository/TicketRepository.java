package com.sprint2.backend.repository;

import com.sprint2.backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Thống kê doanh thu trong khoảng thời gian (ticket)
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object" +
            "('enter_date', project2_parking_management.statistic_total_revenue_ticket.date_payment,\n" +
            "'total_price', project2_parking_management.statistic_total_revenue_ticket.total_price))\n" +
            "from project2_parking_management.statistic_total_revenue_ticket\n" +
            "where project2_parking_management.statistic_total_revenue_ticket.date_payment between (?1) and (?2);")
    Object getTotalRevenueTicketPeriod(LocalDate fromEnterDay, LocalDate toExitDay);


}
