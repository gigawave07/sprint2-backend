package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ParkingSlot;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    ParkingSlot findByFloorContaining(String floor);
    ParkingSlot findByFloor(String floor);
    ParkingSlot findBySlotNumberAndFloor(String slotNumber,String floor);
    List<ParkingSlot> findBySlotNumber(String slotNumber);
    ParkingSlot findByReservedContaining(Boolean reserved);

}
