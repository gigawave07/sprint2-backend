package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.model.ParkingSlotDTODisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.sprint2.backend.repository.SlotTypeRepository;
import com.sprint2.backend.repository.ParkingSlotRepository;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.ParkingSlotDTO;


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
    public List<ParkingSlotDTODisplay> findAllDTO() {
        List<ParkingSlotDTODisplay> parkingSlotDTOList = new ArrayList<>();
        List<ParkingSlot> parkingSlotList = this.parkingSlotRepository.findAll();
        for (ParkingSlot parkingSlot : parkingSlotList) {
            getInfoParkingSlotDTODisplay(parkingSlotDTOList, parkingSlot);
        }
        return parkingSlotDTOList;
    }

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

    @Override
    public ParkingSlot findParkingSlotBySlotNumberAndFloor(String slotNumber, String floor) {
        return this.parkingSlotRepository.findParkingSlotBySlotNumberAndFloor(slotNumber, floor);
    }
    /**
     * MaiHTQ end
     */
}
