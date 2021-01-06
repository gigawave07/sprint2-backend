package com.sprint2.backend.services.parking_slot;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    ParkingSlot findByID(Long id);

    void save(ParkingSlot parkingSlot);

    ParkingSlot findByFloor(String floor);

    ParkingSlot findByReserved(Boolean reserved);

    //Lành

    List<ParkingSlot> findAllNeed();

    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);

    ParkingSlot findBySlotNumber(String slotNumber);

}
