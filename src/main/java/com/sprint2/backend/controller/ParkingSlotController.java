package com.sprint2.backend.controller;

import com.sprint2.backend.model.ParkingSlotDTO;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/parking-slot")
public class ParkingSlotController {
    @Autowired
    ParkingSlotService parkingSlotService;

    // Quan start
    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(parkingSlotService.findAll());
    }

    @PostMapping("/find-by-floor-slot-number")
    public ResponseEntity<?> findByFloorsAndSlotNumber(@RequestBody ParkingSlotDTO parkingSlotDTO) {
        return ResponseEntity.ok(parkingSlotService.findByFloorAndSlotNumber(
                parkingSlotDTO.getFloor(), parkingSlotDTO.getSlotNumber()));
    }
    // Quan end

}
