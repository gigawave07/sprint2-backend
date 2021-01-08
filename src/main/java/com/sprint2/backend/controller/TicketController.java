package com.sprint2.backend.controller;

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
                return ResponseEntity.ok(new MessageDTO("Xếp chỗ thành công"));
            case "no available slot":
                return ResponseEntity.ok(new MessageDTO("Không còn chỗ trống"));
            default:
                return ResponseEntity.ok(new MessageDTO("Xe đã đỗ trong bãi"));
        }
    }

    @PostMapping("/close")
    public ResponseEntity<?> close(@RequestBody TicketDTO ticketDTO) {
        return ticketService.close(ticketDTO) ? ResponseEntity.ok(new MessageDTO("Rời bãi thành công")) :
                ResponseEntity.ok(new MessageDTO("Xe không đỗ trong bãi"));
    }
    // Quan end
}
