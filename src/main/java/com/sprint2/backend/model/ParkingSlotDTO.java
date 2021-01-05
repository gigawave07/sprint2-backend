package com.sprint2.backend.model;

import com.sprint2.backend.entity.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ParkingSlotDTO  {
    private Long id;
    private String floor;
    private String slotNumber;
    private Long slotType;
    private String slotTypeUpdate;
    private String fullName;
    private LocalDate birthDay;
    private String email;
    private String phone;
    private String brandName;
    private String plate_number;
    private Double width;
    private Double height;
    private String slotName;
}
