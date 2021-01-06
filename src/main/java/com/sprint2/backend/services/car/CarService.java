package com.sprint2.backend.services.car;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.CarDTO;

public interface CarService {
    List<Car> findAll();

    Car findByID(Long id);

    // Quan start
    Car findByPlateNumber(String plateNumber);

    void save(Car car);

    Car convert(CarDTO car);
    // Quan end
}
