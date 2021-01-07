package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.net.www.MessageHeader;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {
    // --------------------Vinh begin -----------------------
    @Autowired
    private CarService carService;

    @GetMapping("/scan-qr-code/{id}")
    public ResponseEntity<?> scanQrCode(@PathVariable Long id) {
        Car car = null;
        if (id != null) {
            car = this.carService.findByID(id);
        }
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.ok(new MessageDTO("not found"));
    }

    @GetMapping("/getList-car/{customerId}")
    public ResponseEntity<?> getListCar(@PathVariable Long customerId) {
        List<Car> carList = null;
        if (customerId != null) {
            carList = this.carService.getListCar(customerId);
        }
        return carList != null ? ResponseEntity.ok(carList) : ResponseEntity.ok(new MessageDTO("not found"));
    }

    @GetMapping("amount-of-car/{customerId}")
    public ResponseEntity<?> countCar(@PathVariable Long customerId) {
        List<Car> carList;
        Integer result = 0;
        if (customerId != null) {
            carList = this.carService.getListCar(customerId);
            result = carList.size();
        }
        return ResponseEntity.ok(result);
    }
    // --------------------Vinh end -------------------------

}