package com.sprint2.backend.services.parking_slot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.repository.ParkingSlotRepository;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Override
    public List<ParkingSlot> findAll() {
        return this.parkingSlotRepository.findAll();
    }

    @Override
    public ParkingSlot findByID(Long id) {
        return this.parkingSlotRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ParkingSlot parkingSlot) {
        this.parkingSlotRepository.save(parkingSlot);
    }

    @Override
    public ParkingSlot findByFloor(String floor) {
        return this.parkingSlotRepository.findByFloorContaining(floor);
    }

    @Override
    public ParkingSlot findByReserved(Boolean reserved) {
        return this.parkingSlotRepository.findByReservedContaining(reserved);
    }

    /**
     * Lành
     */
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

    @Override
    public ParkingSlot findBySlotNumber(String slotNumber) {
        return this.parkingSlotRepository.findBySlotNumber(slotNumber);
    }

    @Override
    public ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber) {
        return this.parkingSlotRepository.findByFloorAndSlotNumber(floor, slotNumber);
    }
}
