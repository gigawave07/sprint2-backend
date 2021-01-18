package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    private Long id;

    private String customerCode;

    private String fullName;

    private Boolean gender = false;

    private String email;

    private LocalDate birthday;

    private String phone;

    private String address;

    private String identityNumber;


}
