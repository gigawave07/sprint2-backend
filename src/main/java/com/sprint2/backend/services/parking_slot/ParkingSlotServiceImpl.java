package com.sprint2.backend.services.parking_slot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Thống kê số lượng các hãng xe đang có tại bãi
    @Override
    public Object getTotalCarTypeParkingSlot() {
        return this.parkingSlotRepository.getTotalCarTypeParkingSlot();
    }

    // Thống kê số lượng xe đang có tại bãi
    @Override
    public Long getTotalCarParking() {
        return this.parkingSlotRepository.getTotalCarParking();
    }

    @Override
    public Long getTotalParkingSlot() {
        return this.parkingSlotRepository.getTotalParkingSlot();
    }
}
