package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.sprint2.backend.model.mai_htq.ParkingSlotDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    String plateNumber;
    CarTypeDTO carType;
    ParkingSlotDTO parkingSlot;
}
