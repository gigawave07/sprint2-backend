package com.sprint2.backend.services.lanh_nqn.car;

import com.sprint2.backend.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    List<Car> findPlateNumber(String plateNumber);
}
