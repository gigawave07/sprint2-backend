package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.model.ParkingSlotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.repository.ParkingSlotRepository;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.repository.SlotTypeRepository;

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

    /**
     * MaiHTQ start
     */
    @Override
    public void save(ParkingSlotDTO parkingSlotDTO) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setFloor(parkingSlotDTO.getFloor());
        parkingSlot.setId(parkingSlotDTO.getId());
        parkingSlot.setSlotNumber(parkingSlotDTO.getSlotNumber());
        parkingSlot.setSlotType(this.slotTypeRepository.findById(parkingSlotDTO.getSlotType()).orElse(null));
        parkingSlot.setStatus(false);
        parkingSlot.setReserved(false);
        this.parkingSlotRepository.save(parkingSlot);
    }

    @Override
    public List<SlotType> findAllSlotType() {
        return this.slotTypeRepository.findAll();
    }

    @Override
    public List<ParkingSlot> findParkingSlotByFloor(String floor) {
        return this.parkingSlotRepository.findByFloor(floor);
    }

    @Override
    public ParkingSlot findParkingSlotBySlotNumberAndFloor(String slotNumber, String floor) {
        return this.parkingSlotRepository.findParkingSlotBySlotNumberAndFloor(slotNumber, floor);
    }
    /**
     * MaiHTQ end
     */
}
