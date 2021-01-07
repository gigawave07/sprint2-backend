package com.sprint2.backend.controller;

import com.sprint2.backend.model.ParkingSlotDTODisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import com.sprint2.backend.model.ParkingSlotDTO;

@RestController
@RequestMapping("/parking-slot")
@CrossOrigin
public class ParkingSlotController {
    @Autowired
    private ParkingSlotService parkingSlotService;

    /**
     * Display parking slot list
     * @return parkingSlotList
     * Create by MaiHTQ
     */
    @GetMapping("/list")
    public ResponseEntity<List<ParkingSlotDTODisplay>> getListParkingSlot() {
        List<ParkingSlotDTODisplay> parkingSlotList = this.parkingSlotService.findAllDTO();
        return new ResponseEntity<>(parkingSlotList, HttpStatus.OK);
    }

    /**
     * Display slot type list
     * @return slotTypeList
     * Create by MaiHTQ
     */
    @GetMapping("/slot-type")
    public ResponseEntity<List<SlotType>> getListSlotType() {
        List<SlotType> slotTypeList = this.parkingSlotService.findAllSlotType();
        return new ResponseEntity<>(slotTypeList, HttpStatus.OK);
    }

    /**
     * Create new position parking slot
     * @param parkingSlotDTO
     * @return
     * Create by MaiHTQ
     */
    @PostMapping("/create")
    public ResponseEntity<ParkingSlot> createNewParkingSlot(@RequestBody ParkingSlotDTO parkingSlotDTO) {
        this.parkingSlotService.save(parkingSlotDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Search floor
     * @param floor
     * @return parkingSlotList
     * Create by MaiHTQ
     */
    @GetMapping("/search-floor/{floor}")
    public ResponseEntity<List<ParkingSlotDTODisplay>> searchFloorParkingLot (@PathVariable String floor) {
        List<ParkingSlotDTODisplay> parkingSlotList = this.parkingSlotService.findParkingSlotByFloor(floor);
        return new ResponseEntity<>(parkingSlotList, HttpStatus.OK);
    }

    /**
     * Validate exists slot number and floor
     * @param slotNumber
     * @param floor
     * @return parkingSlot
     * Create by MaiHTQ
     */
    @GetMapping("/find-parking-slot-by-slot-number-floor/{slotNumber}/{floor}")
    public ResponseEntity<ParkingSlot> getParkingSlotById(@PathVariable String slotNumber, @PathVariable String floor) {
        ParkingSlot parkingSlot = this.parkingSlotService.findParkingSlotBySlotNumberAndFloor(slotNumber, floor);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }
}