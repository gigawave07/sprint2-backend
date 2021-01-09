package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.model.ParkingSlotDTO;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/parking-slot")
public class ParkingSlotController {
    @Autowired
    ParkingSlotService parkingSlotService;

    @Autowired
    CarService carService;

    // Quan start
    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(parkingSlotService.findAll());
    }

    @PostMapping("/find-by-floor-slot-number")
    public ResponseEntity<?> findByFloorsAndSlotNumber(@RequestBody ParkingSlotDTO parkingSlotDTO) {
        return ResponseEntity.ok(parkingSlotService.findByFloorAndSlotNumber(
                parkingSlotDTO.getFloor(), parkingSlotDTO.getSlotNumber()));
    }

    @PostMapping("/park-registered-car")
    public ResponseEntity<?> parkRegisteredCar(@RequestBody CarDTO carDTO) {
        Car car = carService.findByPlateNumber(carDTO.getPlateNumber());
        return parkingSlotService.parkRegisteredCar(car) ? ResponseEntity.ok(new MessageDTO("Xe vào bãi thành công")) :
                ResponseEntity.ok(new MessageDTO("Xe đang đỗ trong bãi"));
    }

    @PostMapping("/checkout-registered-car")
    public ResponseEntity<?> checkoutRegisteredCar(@RequestBody CarDTO carDTO) {
        Car car = carService.findByPlateNumber(carDTO.getPlateNumber());
        return parkingSlotService.checkoutRegisteredCar(car) ? ResponseEntity.ok(new MessageDTO("Xe ra bãi thành công")) :
                ResponseEntity.ok(new MessageDTO("Xe không đậu trong bãi"));
    }

    @GetMapping("/find-slot-by-car-id/{id}")
    public ResponseEntity<?> findSlotByCarId(@PathVariable Long id) {
        return ResponseEntity.ok(parkingSlotService.findByCar_Id(id));
    }

    @GetMapping("/find-by-floor/{floor}")
    public ResponseEntity<?> findByFloor(@PathVariable String floor) {
        return ResponseEntity.ok(parkingSlotService.findByFloor(floor));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingSlotService.findByID(id));
    }

    @GetMapping("/find-all-floor")
    public ResponseEntity<?> findAllFloor() {
        return ResponseEntity.ok(parkingSlotService.getAllFloor());
    }

    @GetMapping("/find-available-slot-by-floor/{floor}")
    public ResponseEntity<?> findAvailableSlotsByFloor(@PathVariable String floor) {
        return ResponseEntity.ok(parkingSlotService.findByFloorAndStatusAndReserved(floor, false, false));
    }
    // Quan end

}
