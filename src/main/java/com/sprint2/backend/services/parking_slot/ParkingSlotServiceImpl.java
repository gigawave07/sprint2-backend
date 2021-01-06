package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.repository.ParkingSlotRepository;
import com.sprint2.backend.repository.SlotTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    SlotTypeRepository slotTypeRepository;


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

    // Quan start
    @Override
    public ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber) {
        return this.parkingSlotRepository.findByFloorAndSlotNumber(floor, slotNumber);
    }

    @Override
    public ParkingSlot arrangeSlot(Car car) {
        CarType carType = car.getCarType();
        SlotType slotType;
        switch (carType.getCarTypeName()) {
            case "4 chỗ":
            case "7 chỗ":
                slotType = slotTypeRepository.findById(1L).orElse(null);
                break;
            case "9 chỗ":
            case "Bán tải":
                slotType = slotTypeRepository.findById(2L).orElse(null);
                break;
            default:
                slotType = slotTypeRepository.findById(3L).orElse(null);
        }

        List<ParkingSlot> parkingSlotList = findBySlotTypeAndStatus(slotType, false);

        if (parkingSlotList.size() == 0) {
            return null;
        }

        return parkingSlotList.get(0);
    }

    @Override
    public List<ParkingSlot> findBySlotTypeAndStatus(SlotType slotType, Boolean status) {
        return parkingSlotRepository.findBySlotTypeAndStatus(slotType, status);
    }


    // Quan end
}
