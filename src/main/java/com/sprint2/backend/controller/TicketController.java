package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Ticket;
import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.model.TicketDTO;
import com.sprint2.backend.services.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    // Quan start

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TicketDTO ticketDTO) {
        String message = ticketService.save(ticketDTO);
        switch (message) {
            case "succeed":
                return ResponseEntity.ok(new MessageDTO("succeed"));
            case "no available slot":
                return ResponseEntity.ok(new MessageDTO("no available slot"));
            default:
                return ResponseEntity.ok(new MessageDTO("car is already parked"));
        }
    }

    @PostMapping("/close")
    public ResponseEntity<?> close(@RequestBody TicketDTO ticketDTO) {
        return ticketService.close(ticketDTO) ? ResponseEntity.ok(new MessageDTO("succeed")) :
                ResponseEntity.ok(new MessageDTO("Car is not parked"));
    }
    // Quan end
}
