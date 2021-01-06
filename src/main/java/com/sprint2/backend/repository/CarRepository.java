package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    /**
     * Lành
     */
    Car findByPlateNumber(String plateNumber);

    List<Car> findCarByPlateNumber (String plateNumber);
}
