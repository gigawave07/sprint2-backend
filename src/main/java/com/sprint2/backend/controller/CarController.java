package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.repository.CarTypeRepository;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sprint2.backend.entity.Car;
import com.sprint2.backend.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @Autowired
    CarTypeRepository carTypeRepository;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Autowired
    MemberCardRepository memberCardRepository;

    // Quan start
    @PostMapping("/get-info")
    public ResponseEntity<?> getInfoByPlateNumber(@RequestBody CarDTO carDTO) {
        Car car = carService.findByPlateNumber(carDTO.getPlateNumber());
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.ok(new MessageDTO("Không tìm thấy xe"));
    }

    @GetMapping("/find-all-type")
    public ResponseEntity<?> findAllType() {
        return ResponseEntity.ok(carTypeRepository.findAll());
    }

    @PostMapping("/find-all-member-card")
    public ResponseEntity<?> findAllMemberCardByCar(@RequestBody CarDTO carDTO) {
        List<MemberCard> memberCardList = memberCardRepository.findByCar_PlateNumber(carDTO.getPlateNumber());
        return memberCardList != null ? ResponseEntity.ok(memberCardList) :
                ResponseEntity.ok(new MessageDTO("Ko đăng kí vé dài hạn"));
    }
    // Quan end

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
