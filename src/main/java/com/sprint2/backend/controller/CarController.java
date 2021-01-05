package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {
    // --------------------Vinh begin -----------------------
    @Autowired
    private CarService carService;

    @GetMapping("/scanQrCode/{id}")
    public ResponseEntity<Car> scanQrCode(@PathVariable Long id) {
        return ResponseEntity.ok(this.carService.findByID(id));
    }

    @GetMapping("/getListCar/{customerId}")
    public ResponseEntity<List<Car>> getListCar(@PathVariable Long customerId) {
        return ResponseEntity.ok(this.carService.getListCar(customerId));
    }
    // --------------------Vinh end -------------------------
}
