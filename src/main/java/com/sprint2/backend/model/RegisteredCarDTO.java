package com.sprint2.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.entity.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredCarDTO {
    private Long id;

    private String plateNumber;

    private String brandName;

    private String customerIdentityNumber;

    private Long carTypeId;

}
