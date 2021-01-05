package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.RegisteredCarDTO;
import com.sprint2.backend.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registered-car")
@CrossOrigin
public class RegisteredCarController {
    @Autowired
    private CarService carService;

    /***
     * Display registered car list
     * @return carList
     * Created by DangNH
     */
    @GetMapping("/list")
    public ResponseEntity<List<Car>> getListRegisteredCar() {
        List<Car> carList = this.carService.findAllRegisteredCar();
        return carList.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(carList, HttpStatus.OK);
    }

    /***
     * Add new registered car
     * @param registeredCarDTO RegisteredCarDTO
     * @return car Car
     * Created by DangNH
     */
    @PostMapping("/add")
    public ResponseEntity<Car> addRegisteredCar(@RequestBody RegisteredCarDTO registeredCarDTO) {
        Car car = this.carService.convertRegisteredCarDTOToCar(registeredCarDTO);
        if (car.getCustomer() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    /***
     * Edit registered car
     * @param id Long
     * @param registeredCarDTO RegisteredCarDTO
     * @return newCar Car
     * Created by DangNH
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Car> editRegisteredCar(@PathVariable Long id, @RequestBody RegisteredCarDTO registeredCarDTO) {
        Car oldCar = this.carService.findByID(id);
        if (oldCar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Car newCar = this.carService.convertRegisteredCarDTOToCar(registeredCarDTO);
        newCar.setId(id);
        newCar.setCustomer(oldCar.getCustomer());
        this.carService.save(newCar);
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }

    /***
     * Delete registered car
     * @param id Long
     * @return
     * Created by DangNH
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Car> deleteRegisteredCar(@PathVariable Long id) {
        Car oldCar = this.carService.findByID(id);
        if (oldCar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldCar.setCustomer(null);
        oldCar.setCarType(null);
        this.carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
