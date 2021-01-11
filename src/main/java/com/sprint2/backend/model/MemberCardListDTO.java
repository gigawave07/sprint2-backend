package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCardListDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private Double price;
    private String nameType;
    private String plateNumber;
    private String nameCustomer;
    private String floor;
    private String slotNumber;
    private Long slotType;
}
