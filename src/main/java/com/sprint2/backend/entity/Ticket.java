package com.sprint2.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "enter_date", columnDefinition = "DATETIME")
    private LocalDateTime enterDate;

    @Column(name = "exit_date", columnDefinition = "DATETIME")
    private LocalDateTime exitDate;

    @Column(name = "price", columnDefinition = "DOUBLE")
    private Double price;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Car car;
}
