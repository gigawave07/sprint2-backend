package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "plate_number", columnDefinition = "VARCHAR(50)")
    private String plateNumber;

    @Column(name = "brand_name", columnDefinition = "VARCHAR(50)")
    private String brandName;

    @Column(name = "image_plate_number", columnDefinition = "TEXT")
    private String imagePlateNumber;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_type_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private CarType carType;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
    private ParkingSlot parkingSlot;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
    private List<Ticket> ticketList;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
    private List<MemberCard> memberCardList;

    // custom constructor

    public Car(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
