package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String carTypeName;
    private String brandName;
    private String plateNumber;
    private String customer;
    private String startDate;
    private String endDate;
    private String memberType;

}
