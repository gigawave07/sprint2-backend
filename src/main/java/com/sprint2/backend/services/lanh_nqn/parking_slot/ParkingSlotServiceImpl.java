package com.sprint2.backend.services.lanh_nqn.parking_slot;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.repository.lanh_nqn.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Override
    public List<ParkingSlot> findAll() {
        return this.parkingSlotRepository.findAll();
    }

    @Override
    public List<ParkingSlot> findAllNeed() {
        List<ParkingSlot> parkingSlotListExists = this.parkingSlotRepository.findAll();
        List<ParkingSlot> parkingSlotListDisplay = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingSlotListExists) {
            if (!parkingSlot.getReserved() && !parkingSlot.getStatus()) {
                parkingSlotListDisplay.add(parkingSlot);
            }
        }
        return parkingSlotListDisplay;
    }
}
