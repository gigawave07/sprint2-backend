package com.sprint2.backend.services.parking_slot;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.ParkingSlotDTO;
import com.sprint2.backend.model.ParkingSlotDTODisplay;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    List<ParkingSlotDTODisplay> findAllDTO();

    ParkingSlot findByID(Long id);

    void save(ParkingSlotDTO parkingSlotDTO);

    List<SlotType> findAllSlotType();

    List<ParkingSlotDTODisplay> findParkingSlotByFloor (String floor);

    ParkingSlot findParkingSlotBySlotNumberAndFloor(String slotNumber, String floor);

    //Lành

    List<ParkingSlot> findAllNeed();

    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);

    ParkingSlot findBySlotNumber(String slotNumber);
}
