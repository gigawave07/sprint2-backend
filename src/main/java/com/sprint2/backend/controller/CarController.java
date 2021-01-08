package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.android.CarAppVinh;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private MemberCardService memberCardService;

    @Autowired
    private ParkingSlotService parkingSlotService;
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
    public ResponseEntity<?> getListCar(@PathVariable Long customerId) {
        List<Car> carList;
        List<CarAppVinh> carAppVinhList = new ArrayList<>();
        List<MessageDTO> messageDTOList = new ArrayList<>();
        List<MemberCard> memberCardList;
        List<ParkingSlot> parkingSlotList;
        messageDTOList.add(new MessageDTO("not found"));
        if (customerId != null) {
            carList = this.carService.getListCar(customerId);
            CarAppVinh carAppVinh = new CarAppVinh();
            CarAppVinh temp = new CarAppVinh();
            for (Car car : carList) {
                carAppVinh.setId(car.getId().toString());
                carAppVinh.setLicensePlate(car.getPlateNumber());
                carAppVinh.setCarType(car.getCarType().getCarTypeName());
                // Lấy dữ liệu ngày hết hạn từ memberCard
                memberCardList = this.memberCardService.findAllByCarId(car.getId());
                // Định dạng lại Date Time thành String xóa chữ T ở giữa date và time
                carAppVinh.setBeginDate(memberCardList.get(memberCardList.size() - 1).
                        getStartDate().toString().replace('T', ' '));
                carAppVinh.setEndDate(memberCardList.get(memberCardList.size() - 1).
                        getEndDate().toString().replace('T', ' '));
                // -----------------------------------------------------------------------------------------------------
                carAppVinh.setTypeCard(memberCardList.get(memberCardList.size() - 1).getMemberCardType().
                        getMemberTypeName());
                // Lấy tầng trong parking slot
                parkingSlotList = this.parkingSlotService.findAllByCarId(car.getId());
                carAppVinh.setFloor(parkingSlotList.get(parkingSlotList.size() - 1).getFloor());
                // -----------------------------------------------------------------------------------------------------
                // Lấy vị trí trong parking slot type


                carAppVinhList.add(carAppVinh);
                carAppVinh = temp;
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
        return ResponseEntity.ok(new MessageDTO("" + carList.size()));
    }
    // --------------------Vinh end -------------------------

}