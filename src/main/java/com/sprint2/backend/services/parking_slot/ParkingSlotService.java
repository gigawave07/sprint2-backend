package com.sprint2.backend.services.parking_slot;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.ParkingSlotDTO;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    ParkingSlot findByID(Long id);

    void save(ParkingSlotDTO parkingSlotDTO);

    List<SlotType> findAllSlotType();

    List<ParkingSlot> findParkingSlotByFloor (String floor);

    ParkingSlot findParkingSlotBySlotNumberAndFloor(String slotNumber, String floor);
}
