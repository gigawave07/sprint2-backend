package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;

import java.util.List;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    ParkingSlot findByID(Long id);

    void save(ParkingSlot parkingSlot);

    ParkingSlot findByFloor(String floor);

    ParkingSlot findByReserved(Boolean reserved);

    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);

    ParkingSlot arrangeSlot(Car car);

    List<ParkingSlot> findBySlotTypeAndStatus(SlotType slotType, Boolean status);
}
