package com.sprint2.backend.controller;

import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.ParkingSlotDTO;
import com.sprint2.backend.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;
    // Quan start
    @PostMapping("/get-info")
    public ResponseEntity<?> getInfoByPlateNumber(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.findByPlateNumber(carDTO.getPlateNumber()));
    }
    // Quan end
}
