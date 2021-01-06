package com.sprint2.backend.services.car;

import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.repository.CustomerRepository;
import com.sprint2.backend.services.CarType.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car findByID(Long id) {
        return this.carRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCar (CustomerDTO customerDTO) {
        Customer customer1 = customerRepository.findCustomerByCustomerCode(customerDTO.getCustomerCode());
         CarType car_type_id = carTypeService.findCarTypeByName(customerDTO.getCarTypeName());
        Car car = new Car();
        car.setBrandName(customerDTO.getBrandName());
        car.setPlateNumber(customerDTO.getPlateNumber());
//        car.setCarType(customerDTO.getCar_type_id());
        car.setCustomer(customer1);
        this.carRepository.save(car);
    }
}
