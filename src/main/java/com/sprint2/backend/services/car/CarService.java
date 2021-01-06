package com.sprint2.backend.services.car;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.CustomerDTO;

public interface CarService {
    List<Car> findAll();
    Car findByID(Long id);
    void saveCar (CustomerDTO customerDTO);
}
