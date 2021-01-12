package com.sprint2.backend.services.parking_slot;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    ParkingSlot findByID(Long id);

    void save(ParkingSlot parkingSlot);

    List<ParkingSlot> findBySlotNumber(String slotNumber);

    ParkingSlot findBySlotNumberAndFloor(String slotNumber, String floor);

    ParkingSlot findByFloor(String floor);

    ParkingSlot findByReserved(Boolean reserved);

    List<SlotType> findAllSlotType();
}
