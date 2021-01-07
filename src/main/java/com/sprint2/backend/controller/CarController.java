package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.android.CarAppVinh;
import com.sprint2.backend.model.MessageDTO;
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

    @GetMapping("/scan-qr-code/{id}")
    public ResponseEntity<?> scanQrCode(@PathVariable Long id) {
        Car car = null;
        if (id != null) {
            car = this.carService.findByID(id);
        }
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.ok(new MessageDTO("not found"));
    }

    @GetMapping("/get-list-car/{customerId}")
    public ResponseEntity<?> getListCar(@PathVariable Long customerId) {
        List<Car> carList = null;
        List<CarAppVinh> carAppVinhList = null;
        if (customerId != null) {
            carList = this.carService.getListCar(customerId);
        }
        CarAppVinh carAppVinh = new CarAppVinh();
        for (Car car : carList){
            carAppVinh.setId(car.getId().toString());
            carAppVinh.setLicensePlate(car.getPlateNumber());
            carAppVinh.setCarTypeId(car.getCarType().getId().toString());
            carAppVinhList.add(carAppVinh);
        }

        return carList != null ? ResponseEntity.ok(carAppVinhList) : ResponseEntity.ok(new MessageDTO("not found"));
    }

    @GetMapping("amount-of-car/{customerId}")
    public ResponseEntity<?> countCar(@PathVariable Long customerId) {
        List<Car> carList = null;
        if (customerId != null) {
            carList = this.carService.getListCar(customerId);
        }
        return ResponseEntity.ok(new MessageDTO("" + carList.size()));
    }
    // --------------------Vinh end -------------------------

}