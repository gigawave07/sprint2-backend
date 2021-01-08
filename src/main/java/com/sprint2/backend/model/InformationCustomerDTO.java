package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// Đin
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformationCustomerDTO {
    Long id;
    String fullName;
    Boolean gender;
    String email;
    LocalDate birthday;
    String phone;
    String address;
    String identityNumber;
    String imageAvatar;
}
// End
