package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCardEditDTO {
    private Long id;
    private String idCar;
    private String fullName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private Long numberSlot;
    private String floor;
    private String memberCardType;
}
