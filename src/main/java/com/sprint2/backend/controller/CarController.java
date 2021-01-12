package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.android.CarAppVinh;
import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.repository.CarTypeRepository;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {
    @Autowired
    CarService carService;

    @Autowired
    CarTypeRepository carTypeRepository;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Autowired
    MemberCardRepository memberCardRepository;

    @Autowired
    private MemberCardService memberCardService;

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
     *
     * @param id
     * @param page
     * @return get list car with sort and pagination
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


    // --------------------Vinh begin -----------------------

    @GetMapping("/scan-qr-code/{id}")
    public ResponseEntity<?> scanQrCode(@PathVariable Long id) {
        Car car = null;
        if (id != null) {
            car = this.carService.findByID(id);
        }
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.ok(new MessageDTO("not found"));
    }

    @GetMapping("/get-list-car/{customerId}")
    public ResponseEntity<?> getListCar(@PathVariable Long customerId) throws ParseException {
        List<Car> carList;
        List<CarAppVinh> carAppVinhList = new ArrayList<>();
        List<MessageDTO> messageDTOList = new ArrayList<>();
        List<MemberCard> memberCardList;
        ParkingSlot parkingSlot;
        messageDTOList.add(new MessageDTO("not found"));
        if (customerId != null) {
            carList = this.carService.getListCar(customerId);
            for (Car car : carList) {
                parkingSlot = this.parkingSlotService.findByCar(car);
                if (parkingSlot == null || !parkingSlot.getReserved()) {
                    carList.remove(car);
                    break;
                }
                CarAppVinh carAppVinh = new CarAppVinh();
                carAppVinh.setId(car.getId().toString());
                carAppVinh.setLicensePlate(car.getPlateNumber());
                carAppVinh.setCarType(car.getCarType().getCarTypeName());
                // Lấy dữ liệu ngày hết hạn từ memberCard
                memberCardList = this.memberCardService.findAllByCarId(car.getId());
                if (memberCardList.size() != 0) {
                    carAppVinh.setBeginDate(memberCardList.get(memberCardList.size() - 1).getStartDate().
                            toLocalDate().toString());
                    carAppVinh.setEndDate(memberCardList.get(memberCardList.size() - 1).getEndDate().
                            toLocalDate().toString());
                    carAppVinh.setTypeCard(memberCardList.get(memberCardList.size() - 1).getMemberCardType().
                            getMemberTypeName());
                } else {
                    break;
                }
                // Lấy tầng trong parking slot
                parkingSlot = this.parkingSlotService.findByCar_Id(car.getId());
                if (parkingSlot != null) {
                    carAppVinh.setFloor(parkingSlot.getFloor());
                    carAppVinh.setSlotNum(parkingSlot.getSlotNumber());
                }
                carAppVinhList.add(carAppVinh);
            }
        }
        return carAppVinhList.size() != 0 ? ResponseEntity.ok(carAppVinhList) : ResponseEntity.ok(messageDTOList);
    }

    @GetMapping("/get-list-car/")
    public ResponseEntity<?> getListCarFail() {
        return ResponseEntity.ok(new MessageDTO("not found"));
    }

    @GetMapping("amount-of-car/{customerId}")
    public ResponseEntity<?> countCar(@PathVariable Long customerId) {
        List<Car> carList = null;
        if (customerId != null) {
            carList = this.carService.getListCar(customerId);
        }
        for (int i = 0; i < carList.size(); i++) {
            ParkingSlot parkingSlot = this.parkingSlotService.findByCar_Id(carList.get(i).getId());
            if (parkingSlot == null || !parkingSlot.getReserved()) {
                carList.remove(i);
                i--;
            }
        }
        return ResponseEntity.ok(new MessageDTO("" + carList.size()));
    }
    // --------------------Vinh end -------------------------

}

