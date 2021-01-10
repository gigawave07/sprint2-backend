package com.sprint2.backend.services.carNgan;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.NganCustomerDTO;
import com.sprint2.backend.repository.CarTypeRepository;
import com.sprint2.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.CarRepository;

//Ngan's tasks
@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarTypeRepository carTypeRepository;
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
    public void saveCar (NganCustomerDTO nganCustomerDTO) {
        Customer customer1 = customerRepository.findCustomerByCustomerCode(nganCustomerDTO.getCustomerCode());
        System.out.println(customer1);
        Car car = new Car();
        car.setBrandName(nganCustomerDTO.getBrandName());
        car.setPlateNumber(nganCustomerDTO.getPlateNumber());
        car.setCarType(this.carTypeRepository.findById(nganCustomerDTO.getCarType()).orElse(null));
        car.setCustomer(customer1);
        this.carRepository.save(car);
    }
}
//End Ngan's tasks 
