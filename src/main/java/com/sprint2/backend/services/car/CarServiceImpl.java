package com.sprint2.backend.services.car;

import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.RegisteredCarDTO;
import com.sprint2.backend.repository.CarTypeRepository;
import com.sprint2.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;


    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    /**
     * Đăng update start
     */
    @Override
    public Car findByID(Long id) {
        return this.carRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        this.carRepository.deleteById(id);
    }

    /**
     * Đăng update end
     */

    @Override
    public void save(Car car) {
        this.carRepository.save(car);
    }

    /**
     * Đăng start
     */
    @Override
    public List<Car> findAllRegisteredCar() {
        return this.carRepository.findAllByCustomerIsNotNull();
    }

    @Override
    public Car convertRegisteredCarDTOToCar(RegisteredCarDTO registeredCarDTO) {
        Car car = new Car();
        car.setId(registeredCarDTO.getId());
        car.setBrandName(registeredCarDTO.getBrandName());
        car.setPlateNumber(registeredCarDTO.getPlateNumber());
        Customer customer = this.customerRepository.findByIdentityNumber(registeredCarDTO.getCustomerIdentityNumber());
        car.setCustomer(customer);
        CarType carType = this.carTypeRepository.findById(registeredCarDTO.getCarTypeId()).orElse(null);
        car.setCarType(carType);
        return car;
    }
    /**
     * Đăng end
     */
}
