package com.example.sprin2_mobile.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    String id;
    String licensePlate;
    String carType;
    String beginDate;
    String endDate;
    String typeCard;
    String floor;
    String slotNum;
}
