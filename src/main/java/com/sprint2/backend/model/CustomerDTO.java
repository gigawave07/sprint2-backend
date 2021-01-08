package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
