package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
//    Nhật Kiểm tra xe có trong db ko
    Car getCarByPlateNumber(String plateNumber);
}
