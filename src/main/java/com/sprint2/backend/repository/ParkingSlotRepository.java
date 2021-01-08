package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ParkingSlot;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByFloor(String floor);

    ParkingSlot findParkingSlotBySlotNumberAndFloor (String slotNumber, String floor);
    ParkingSlot findByReservedContaining(Boolean reserved);


    /**
     * Lành
     */
    ParkingSlot findBySlotNumber(String slotNumber);

    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);
}
