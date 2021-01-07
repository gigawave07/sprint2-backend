package com.sprint2.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "parking_slot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "slot_number", columnDefinition = "VARCHAR(50)")
    private String slotNumber;

    @Column(name = "floor", columnDefinition = "VARCHAR(50)")
    private String floor;

    @Column(name = "reserved", columnDefinition = "BIT")
    private Boolean reserved = false;

    @Column(name = "status", columnDefinition = "BIT")
    private Boolean status = false;

    // relationship
    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "slot_type_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private SlotType slotType;
}
