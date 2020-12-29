package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "slot_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlotType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "slot_name", columnDefinition = "VARCHAR(50)")
    private String slotName;

    @Column(name = "height", columnDefinition = "DOUBLE")
    private Double height;

    @Column(name = "width", columnDefinition = "DOUBLE")
    private Double width;

    // relationship

    @OneToMany(mappedBy = "slotType", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ParkingSlot> parkingSlotList;
}
