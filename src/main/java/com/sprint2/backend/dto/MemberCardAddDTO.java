package com.sprint2.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCardAddDTO {
    private String plateNumber;
    private String fullName;
    private Long memberTypeID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long slotNumber;
    private Double price;
}
