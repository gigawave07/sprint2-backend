package com.sprint2.backend.repository;

import com.sprint2.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    /**
     * Lành start
     */
    Car findByPlateNumber(String plateNumber);

    List<Car> findCarByPlateNumber(String plateNumber);

    /**
     * Lành end
     */
}
