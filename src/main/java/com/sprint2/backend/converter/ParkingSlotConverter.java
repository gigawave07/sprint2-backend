package com.sprint2.backend.converter;

import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.model.ParkingSlotDTO;
import org.springframework.stereotype.Component;

@Component
public class ParkingSlotConverter {
    public ParkingSlot toParkingSlot(ParkingSlotDTO parkingSlotDTO) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setFloor(parkingSlotDTO.getFloor());
        parkingSlot.setSlotNumber(parkingSlotDTO.getSlotNumber());
        parkingSlot.setSlotType(parkingSlot.getSlotType());
        return parkingSlot;
    }
    public ParkingSlotDTO toParkingSlotDTO(ParkingSlot parkingSlot){
        ParkingSlotDTO parkingSlotDTO = new ParkingSlotDTO();
        try{
            parkingSlotDTO.setId(parkingSlot.getId());
            parkingSlotDTO.setFloor(parkingSlot.getFloor());
            parkingSlotDTO.setSlotNumber(parkingSlot.getSlotNumber());
            parkingSlotDTO.setWidth(parkingSlot.getSlotType().getWidth());
            parkingSlotDTO.setHeight(parkingSlot.getSlotType().getHeight());
            parkingSlotDTO.setSlotName(parkingSlot.getSlotType().getSlotName());
            parkingSlotDTO.setFullName(parkingSlot.getCar().getCustomer().getFullName());
            parkingSlotDTO.setEmail(parkingSlot.getCar().getCustomer().getEmail());
            parkingSlotDTO.setBirthDay(parkingSlot.getCar().getCustomer().getBirthday());
            parkingSlotDTO.setPhone(parkingSlot.getCar().getCustomer().getPhone());
            parkingSlotDTO.setPlate_number(parkingSlot.getCar().getPlateNumber());
            parkingSlotDTO.setBrandName(parkingSlot.getCar().getBrandName());
            return parkingSlotDTO;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return parkingSlotDTO;
    }
}
