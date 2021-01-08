package com.sprint2.backend.services.lanh_nqn.parking_slot;

import com.sprint2.backend.entity.ParkingSlot;

import java.util.List;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    List<ParkingSlot> findAllNeed();

}
