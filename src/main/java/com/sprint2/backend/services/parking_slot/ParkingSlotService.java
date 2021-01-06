package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.entity.ParkingSlot;

import java.util.List;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    ParkingSlot findByID(Long id);

    void save(ParkingSlot parkingSlot);

    ParkingSlot findByFloor(String floor);

    ParkingSlot findByReserved(Boolean reserved);


    // Thống kê số lượng các hãng xe đang có tại bãi
    Object getTotalCarTypeParkingSlot();

    // Thống kê số lượng xe đang có tại bãi
    Long getTotalCarParking();

    // Thống kê tổng số lượng vị trí đỗ xe của bãi
    Long getTotalParkingSlot();
}
