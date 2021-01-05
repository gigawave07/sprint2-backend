package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MemberCardExpiredDTO {
    private Long id;
    private String plateNumber;
    private String brandName;
    private String phone;
    private String email;
    private LocalDate birthday;
    private String fullName;
    private String slotNumber;
    private String floor;
}
