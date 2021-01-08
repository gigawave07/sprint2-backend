package com.sprint2.backend.services.lanh_nqn.car;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.lanh_nqn.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }


    @Override
    public List<Car> findPlateNumber(String plateNumber) {
        return this.carRepository.findCarByPlateNumber(plateNumber);
    }
}
