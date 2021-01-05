package com.sprint2.backend.services.car;

import java.util.List;

import com.sprint2.backend.entity.Car;

public interface CarService {
    List<Car> findAll();

    Car findByID(Long id);

    // --------------------Vinh begin -----------------------
    List<Car> getListCar(Long customerId);
    // --------------------Vinh end -------------------------

}
