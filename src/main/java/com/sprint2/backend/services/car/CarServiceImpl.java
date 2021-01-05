package com.sprint2.backend.services.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car findByID(Long id) {
        return this.carRepository.findById(id).orElse(null);
    }

    // --------------------Vinh begin -----------------------
    @Override
    public List<Car> getListCar(Long customerId) {
        return this.carRepository.findAllByCustomerId(customerId);
    }
    // --------------------Vinh end -------------------------
}
