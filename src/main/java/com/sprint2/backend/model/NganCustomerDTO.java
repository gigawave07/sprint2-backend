package com.sprint2.backend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//Ngan's tasks
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NganCustomerDTO {
    private String customerCode;
    private String identityNumber;
    private String fullName;
    private Boolean gender;
    private String email;
    private LocalDate birthday;
    private String phone;
    private String address;
    private Long carType;
    private String brandName;
    private String plateNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long memberCardType;
}
//End Ngan's tasks
