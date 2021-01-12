package com.sprint2.backend.repository.mai_htq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ParkingSlot;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    ParkingSlot findParkingSlotBySlotNumberAndFloor (String slotNumber, String floor);
}
