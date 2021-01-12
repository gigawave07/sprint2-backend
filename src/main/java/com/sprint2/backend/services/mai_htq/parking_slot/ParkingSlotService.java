package com.sprint2.backend.services.mai_htq.parking_slot;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.mai_htq.ParkingSlotDTO;
import com.sprint2.backend.model.mai_htq.ParkingSlotDTODisplay;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    List<ParkingSlotDTODisplay> findAllDTO();

    ParkingSlot findByID(Long id);

    void save(ParkingSlotDTO parkingSlotDTO);

    List<SlotType> findAllSlotType();

    List<ParkingSlotDTODisplay> findParkingSlotByFloor (String floor);

    ParkingSlot findParkingSlotBySlotNumberAndFloor(String slotNumber, String floor);
}
