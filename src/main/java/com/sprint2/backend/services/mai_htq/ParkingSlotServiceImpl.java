package com.sprint2.backend.services.mai_htq;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.repository.EntryLogRepository;
import com.sprint2.backend.repository.ParkingSlotRepository;
import com.sprint2.backend.repository.SlotTypeRepository;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.entity.ParkingSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.mai_htq.ParkingSlotDTO;
import com.sprint2.backend.model.mai_htq.ParkingSlotDTODisplay;


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


    // MaiHTQ start
    /**
     * Find All list
     * @return parkingSlotDTOList
     * Create by MaiHTQ
     */
    @Override
    public List<ParkingSlotDTODisplay> findAllDTO() {
        List<ParkingSlotDTODisplay> parkingSlotDTOList = new ArrayList<>();
        List<ParkingSlot> parkingSlotList = this.parkingSlotRepository.findAll();
        for (ParkingSlot parkingSlot : parkingSlotList) {
            getInfoParkingSlotDTODisplay(parkingSlotDTOList, parkingSlot);
        }
        return parkingSlotDTOList;
    }

    /**
     * Create new position parking slot
     * @param parkingSlotDTO
     * Create by MaiHTQ
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

    /**
     * Find All Slot Type
     * @return list slot type
     * Create by MaiHTQ
     */
    @Override
    public List<SlotType> findAllSlotType() {
        return this.slotTypeRepository.findAll();
    }

    /**
     * Search floor
     * @param floor
     * @return list
     * Create by MaiHTQ
     */
    @Override
    public List<ParkingSlotDTODisplay> findParkingSlotByFloor(String floor) {
        List<ParkingSlotDTODisplay> parkingSlotDTOList = new ArrayList<>();
        List<ParkingSlot> parkingSlotList = this.parkingSlotRepository.findAll();
        for (ParkingSlot parkingSlot : parkingSlotList) {
            if (parkingSlot.getFloor().equals(floor)) {
                getInfoParkingSlotDTODisplay(parkingSlotDTOList, parkingSlot);
            }
        }
        return parkingSlotDTOList;
    }

    /**
     * Get Information Parking Slot DTO
     * @param parkingSlotDTOList
     * @param parkingSlot
     * Create by MaiHTQ
     */
    public void getInfoParkingSlotDTODisplay(List<ParkingSlotDTODisplay> parkingSlotDTOList, ParkingSlot parkingSlot) {
        ParkingSlotDTODisplay parkingSlotDTO = new ParkingSlotDTODisplay();
        parkingSlotDTO.setFloor(parkingSlot.getFloor());
        parkingSlotDTO.setSlotNumber(Long.parseLong(parkingSlot.getSlotNumber()));
        if (parkingSlot.getReserved()) {
            parkingSlotDTO.setReversed(1L);
        } else {
            parkingSlotDTO.setReversed(0L);
        }
        parkingSlotDTOList.add(parkingSlotDTO);
    }

    /**
     * Validate Exists
     * @param slotNumber
     * @param floor
     * @return parking slot
     * Create by MaiHTQ
     */
    @Override
    public ParkingSlot findParkingSlotBySlotNumberAndFloor(String slotNumber, String floor) {
        return this.parkingSlotRepository.findParkingSlotBySlotNumberAndFloor(slotNumber, floor);
    }
    // MaiHTQ end

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

    @Override
    public List<ParkingSlot> findByFloor(String floor) {
        return parkingSlotRepository.findByFloor(floor);
    }

    @Override
    public List<String> getAllFloor() {
        return parkingSlotRepository.getAllFloor();
    }

    @Override
    public List<ParkingSlot> findByFloorAndStatusAndReserved(String floor, Boolean status, Boolean reserved) {
        return parkingSlotRepository.findByFloorAndStatusAndReserved(floor, status, reserved);
    }


    // Quan end


    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê số lượng các hãng xe đang có tại bãi
     */
    @Override
    public Object getTotalCarTypeParkingSlot() {
        return this.parkingSlotRepository.getTotalCarTypeParkingSlot();
    }


    /**
     * @return Thống kê số lượng xe đang có tại bãi
     */
    @Override
    public Long getTotalCarParking() {
        return this.parkingSlotRepository.getTotalCarParking();
    }

    /**
     * End
     *
     * @return Thống kê số lượng vị trí đỗ xe của bãi
     */
    @Override
    public Long getTotalParkingSlot() {
        return this.parkingSlotRepository.getTotalParkingSlot();
    }

    // -------------------------------- Vinh Begin ----------------------------------------

    @Override
    public ParkingSlot findAllByCarId(Long carId) {
        return this.parkingSlotRepository.findByCarIdAndStatusTrue(carId);
    }

    // -------------------------------- Vinh End ------------------------------------------
}
