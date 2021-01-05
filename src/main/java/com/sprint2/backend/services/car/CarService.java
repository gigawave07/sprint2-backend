package com.sprint2.backend.services.car;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.RegisteredCarDTO;

public interface CarService {
    List<Car> findAll();

    /**
     * Đăng update start
     */
    Car findByID(Long id);

    void deleteById(Long id);
    /**
     * Đăng update end
     */

    void save(Car car);

    /**
     * Đăng start
     */

    List<Car> findAllRegisteredCar();

    Car convertRegisteredCarDTOToCar(RegisteredCarDTO registeredCarDTO);




    /**
     * Đăng end
     */

}
