package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ParkingSlot;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    ParkingSlot findByFloorContaining(String floor);

    ParkingSlot findByReservedContaining(Boolean reserved);


    /**
     * Lành
     */
    ParkingSlot findBySlotNumber(String slotNumber);

    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);
}
