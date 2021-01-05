package com.sprint2.backend.controller;

import com.sprint2.backend.converter.ParkingSlotConverter;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.ParkingSlotDTO;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-slot")
@CrossOrigin
public class ParkingSlotController {
    @Autowired
    ParkingSlotService parkingSlotService;
    @Autowired
    ParkingSlotConverter parkingSlotConverter;

    @GetMapping("/listParkingSlots")
    public List<ParkingSlot> getListParkingSlot(){
        return this.parkingSlotService.findAll();
    }
    @GetMapping("/parkingSlot/{parkingSlotId}")
    public ParkingSlotDTO getParkingSlotById(@PathVariable("parkingSlotId") Long id) {
        ParkingSlot parkingSlot = this.parkingSlotService.findByID(id);
        ParkingSlotDTO parkingSlotDTO = this.parkingSlotConverter.toParkingSlotDTO(parkingSlot);
        return parkingSlotDTO;
    }

    @PutMapping(value = "/updateParkingSlot/{parkingSlotId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public int updateParkingSlot(@RequestBody ParkingSlotDTO parkingSlotDTO, @PathVariable(value = "parkingSlotId") Long id) {
        try {
            ParkingSlot parkingSlot = this.parkingSlotService.findByID(id);
            parkingSlot.setFloor(parkingSlotDTO.getFloor());
            parkingSlot.setSlotNumber(parkingSlotDTO.getSlotNumber());
            SlotType slotType = parkingSlot.getSlotType();
            slotType.setHeight(parkingSlotDTO.getHeight());
            slotType.setWidth(parkingSlotDTO.getWidth());
            slotType.setSlotName(parkingSlotDTO.getSlotName());
            this.parkingSlotService.save(parkingSlot);
            return 1;
        } catch (Exception e) {
            e.getMessage();
        }
        return 0;
    }
}
