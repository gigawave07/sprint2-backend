package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String employeeCode;
    private String fullName;
    private Boolean gender;
    private String email;
    private String address;
    private LocalDate birthday;
    private String phoneNumber;
    private String position;
    private String password;
    private String role;
}
