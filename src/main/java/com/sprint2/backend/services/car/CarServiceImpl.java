package com.sprint2.backend.services.car;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.CarRepository;
import com.sprint2.backend.model.CarDTO;
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

    // Quan start
    @Override
    public Car findByPlateNumber(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber);
    }

    @Override
    public void save(Car car) {
        this.carRepository.save(car);
    }

    @Override
    public Car convert(CarDTO carDTO) {
        return new Car(carDTO.getPlateNumber());
    }

    // Quan end


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng xe của mỗi khách hàng
     */
    @Override
    public Object getTotalCarOfCustomer() {
        return this.carRepository.getTotalCarOfCustomer();
    }


}
