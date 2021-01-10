package com.sprint2.backend.services.carNgan;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.NganCustomerDTO;
//Ngan's tasks
public interface CarService {
    List<Car> findAll();
    Car findByID(Long id);
    void saveCar (NganCustomerDTO nganCustomerDTO);
}
//End Ngan's tasks
