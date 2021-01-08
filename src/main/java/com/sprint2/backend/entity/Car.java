package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private ParkingSlot parkingSlot;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> ticketList;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MemberCard> memberCardList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImagePlateNumber() {
        return imagePlateNumber;
    }

    public void setImagePlateNumber(String imagePlateNumber) {
        this.imagePlateNumber = imagePlateNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
