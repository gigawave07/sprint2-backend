package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {
    @Autowired
    CarService carService;

    /**
     * nguyen quoc khanh
     * @param id
     * @param page
     * @return
     * get list car with sort and pagination
     */
    @GetMapping("/find-all-car-by-customerId/{id}")
    public ResponseEntity<Page<Car>> getAllCarByCustomerId(@PathVariable Long id,
                                                           @RequestParam(name = "page", defaultValue = "0", required = false)
                                                                   int page) {
        Page<Car> cars = this.carService.findAllCarByCustomerId(id, page);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(cars, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(cars);
    }

}
