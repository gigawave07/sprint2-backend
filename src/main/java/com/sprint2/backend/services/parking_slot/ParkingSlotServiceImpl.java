package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.repository.SlotTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.repository.ParkingSlotRepository;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    @Autowired
    private SlotTypeRepository slotTypeRepository;
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
    public List<ParkingSlot> findBySlotNumber(String slotNumber) {
        return this.parkingSlotRepository.findBySlotNumber(slotNumber);
    }

    @Override
    public ParkingSlot findBySlotNumberAndFloor(String slotNumber, String floor) {
        return this.parkingSlotRepository.findBySlotNumberAndFloor(slotNumber,floor);
    }


    @Override
    public ParkingSlot findByFloor(String floor) {
        return null;
    }


//    @Override
//    public ParkingSlot findByFloor(String floor) {
//        return this.parkingSlotRepository.findByFloor(floor);
//    }


//    @Override
//    public List<ParkingSlot> findBySlotNumber(String slotNumber) {
//        return this.parkingSlotRepository.findBySlotNumber(slotNumber);
//    }


    @Override
    public ParkingSlot findByReserved(Boolean reserved) {
        return this.parkingSlotRepository.findByReservedContaining(reserved);
    }

    @Override
    public List<SlotType> findAllSlotType() {
        return this.slotTypeRepository.findAll();
    }

}
