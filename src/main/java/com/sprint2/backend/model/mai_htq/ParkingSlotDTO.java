package com.sprint2.backend.model.mai_htq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlotDTO {
    private String floor;
    private Long id;
    private String slotNumber;
    private Long slotType;
}
