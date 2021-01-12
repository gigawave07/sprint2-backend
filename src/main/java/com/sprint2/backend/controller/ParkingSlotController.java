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

    @GetMapping("/list")
    public ResponseEntity<List<ParkingSlot>> getListParkingSlot() {
        List<ParkingSlot> parkingSlotList = this.parkingSlotService.findAll();
        return new ResponseEntity<>(parkingSlotList, HttpStatus.OK);
    }

    @GetMapping("/parkingSlot/{parkingSlotId}")
    public ParkingSlotDTO getParkingSlotById(@PathVariable("parkingSlotId") Long id) {
        ParkingSlot parkingSlot = this.parkingSlotService.findByID(id);
        ParkingSlotDTO parkingSlotDTO = this.parkingSlotConverter.toParkingSlotDTO(parkingSlot);
        return parkingSlotDTO;
    }

    @GetMapping("/slot-type")
    public ResponseEntity<List<SlotType>> getListSlotType() {
        List<SlotType> slotTypeList = this.parkingSlotService.findAllSlotType();
        return new ResponseEntity<>(slotTypeList, HttpStatus.OK);
    }

    @PutMapping(value = "/updateParkingSlot/{parkingSlotId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public int updateParkingSlot(@RequestBody ParkingSlotDTO parkingSlotDTO, @PathVariable(value = "parkingSlotId") Long id) {
        try {
            List<ParkingSlot> parkingSlots = this.parkingSlotService.findAll();
            ParkingSlot parkingSlot = this.parkingSlotService.findByID(id);
            parkingSlots.remove(parkingSlot);
            String slotNumber = parkingSlotDTO.getSlotNumber();
            String floor = parkingSlotDTO.getFloor();
            boolean check = true;
            for (int i = 0; i < parkingSlots.size(); i++) {
                if(!parkingSlots.get(i).getSlotNumber().equals(slotNumber) || !parkingSlots.get(i).getFloor().equals(floor) )
                {
                    check = false;
                }else {
                    check = true;
                    break;
                };
            }
            if (check == false) {
                parkingSlot.setFloor(parkingSlotDTO.getFloor());
                parkingSlot.setSlotNumber(parkingSlotDTO.getSlotNumber());
            }else {return 0;}
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
