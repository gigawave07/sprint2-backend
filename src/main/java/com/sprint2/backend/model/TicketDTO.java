package com.sprint2.backend.model;

import com.sprint2.backend.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private LocalDateTime enterDate;

    private LocalDateTime exitDate;

    private Double price;

    private CarDTO car;
}
