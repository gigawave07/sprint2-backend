package com.sprint2.backend.model;

import com.sprint2.backend.entity.MemberCardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long car_id;
    private Long id;
    private String customerCode;
    private String identityNumber;
    private String fullName;
    private Boolean gender;
    private String email;
    private LocalDate birthday;
    private String phone;
    private String address;
    private String carTypeName;
    private String brandName;
    private String plateNumber;
    private Long car_type_id;
    private LocalDate startDate;
    private LocalDate endDate;
    private MemberCardType memberCardType;
    private Long member_car_id;
}
