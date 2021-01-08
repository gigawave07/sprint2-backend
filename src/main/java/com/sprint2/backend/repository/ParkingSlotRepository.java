package com.sprint2.backend.repository;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.SlotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ParkingSlot;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    ParkingSlot findByFloorContaining(String floor);

    ParkingSlot findByReservedContaining(Boolean reserved);

//    Nhật đổi parking slot
    ParkingSlot getParkingSlotByCarId(long idCar);

    // Quan start
    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);

    List<ParkingSlot> findBySlotTypeAndStatusAndReserved(SlotType slotType, Boolean status, Boolean reserved);

    ParkingSlot findByCar(Car car);
    // Quan end
}
