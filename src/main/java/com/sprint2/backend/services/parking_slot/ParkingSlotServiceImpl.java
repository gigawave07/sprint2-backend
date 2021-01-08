package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.repository.EntryLogRepository;
import com.sprint2.backend.repository.ParkingSlotRepository;
import com.sprint2.backend.repository.SlotTypeRepository;
import com.sprint2.backend.services.member_card.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    SlotTypeRepository slotTypeRepository;

    @Autowired
    EntryLogRepository entryLogRepository;

    @Autowired
    MemberCardService memberCardService;

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

        List<ParkingSlot> parkingSlotList = findBySlotTypeAndStatusAndReserved(slotType, false, false);

        if (parkingSlotList.size() == 0) {
            return null;
        }

        return parkingSlotList.get(0);
    }

    @Override
    public List<ParkingSlot> findBySlotTypeAndStatusAndReserved(SlotType slotType, Boolean status, Boolean reserved) {
        return parkingSlotRepository.findBySlotTypeAndStatusAndReserved(slotType, status, reserved);
    }

    @Override
    public ParkingSlot findByCar(Car car) {
        return parkingSlotRepository.findByCar(car);
    }

    @Override
    public Boolean parkRegisteredCar(Car car) {
        ParkingSlot parkingSlot = car.getParkingSlot();
        if (parkingSlot.getStatus()) {
            return false;
        } else {
            parkingSlot.setStatus(true);
        }

        EntryLog entryLog = new EntryLog();
        entryLog.setEnterDate(LocalDateTime.now());

        List<MemberCard> memberCardList = memberCardService.findByPlateNumber(car.getPlateNumber());
        MemberCard memberCard = memberCardList.get(memberCardList.size() - 1);
        entryLog.setMemberCard(memberCard);

        entryLogRepository.save(entryLog);

        save(parkingSlot);
        return true;
    }

    @Override
    public Boolean checkoutRegisteredCar(Car car) {
        ParkingSlot parkingSlot = car.getParkingSlot();
        parkingSlot.setStatus(false);

        List<MemberCard> memberCardList = memberCardService.findByPlateNumber(car.getPlateNumber());
        MemberCard memberCard = memberCardList.get(memberCardList.size() - 1);
        List<EntryLog> entryLogList = entryLogRepository.findByMemberCard(memberCard);
        EntryLog entryLog;
        if (entryLogList != null && entryLogList.size() > 0) {
            entryLog = entryLogList.get(entryLogList.size() - 1);
        } else {
            return false;
        }
        // check current entry log
        if (entryLog.getExitDate() != null) {
            return false;
        } else {
            entryLog.setExitDate(LocalDateTime.now());
        }
        entryLogRepository.save(entryLog);

        save(parkingSlot);
        return true;
    }

    @Override
    public ParkingSlot findByCar_PlateNumber(String plateNumber) {
        return parkingSlotRepository.findByCar_PlateNumber(plateNumber);
    }

    @Override
    public ParkingSlot findByCar_Id(Long id) {
        return parkingSlotRepository.findByCar_Id(id);
    }


    // Quan end
}
