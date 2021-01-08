package com.sprint2.backend.services.car;

import com.sprint2.backend.model.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    /**
     * nguyen quoc khanh
     * sort and pagination
     */
    Sort sort = Sort.by(Sort.Direction.ASC, "brand_name");
    Pageable pageable = PageRequest.of(0,3, sort);

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    /**
     * nguyen quoc khanh
     * @param id
     * @param currentPage
     * @return
     * sort and pagination
     */
    @Override
    public Page<Car> findAllCarByCustomerId(Long id, int currentPage) {
        if(currentPage > 0){
            Pageable pageable = PageRequest.of(currentPage-1,5, sort);
            return this.carRepository.findAllCarByCustomerId(id,pageable);
        }
        return this.carRepository.findAllCarByCustomerId(id, pageable);
    }
    //khanh end
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
}
