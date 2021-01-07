package com.sprint2.backend.services.parking_slot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.sprint2.backend.repository.SlotTypeRepository;
import com.sprint2.backend.repository.ParkingSlotRepository;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.ParkingSlotDTO;
import com.sprint2.backend.model.ParkingSlotDTODisplay;


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
}
