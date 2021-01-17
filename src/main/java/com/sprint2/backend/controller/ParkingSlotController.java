package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.model.mai_htq.ParkingSlotDTO;
import com.sprint2.backend.model.mai_htq.ParkingSlotDTODisplay;
import com.sprint2.backend.repository.SlotTypeRepository;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.mai_htq.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/parking-slot")
public class ParkingSlotController {
    @Autowired
    ParkingSlotService parkingSlotService;

    @Autowired
    CarService carService;

    @Autowired
    SlotTypeRepository slotTypeRepository;

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

    @GetMapping("/unregister-car/{plate}")
    public ResponseEntity<?> unregisterCar(@PathVariable String plate) {
        ParkingSlot parkingSlot = parkingSlotService.findByCar_PlateNumber(plate);
        parkingSlot.setReserved(false);
        parkingSlot.setCar(null);
        parkingSlotService.save(parkingSlot);
        return ResponseEntity.ok(new MessageDTO("OK"));
    }

    @GetMapping("/arrange-slot-registered-car/{plate}")
    public ResponseEntity<?> arrangeSlot(@PathVariable String plate) {
        List<ParkingSlot> parkingSlotList = parkingSlotService.findBySlotTypeAndStatusAndReserved(
                slotTypeRepository.findById(1L).orElse(null), false, false);
        if (parkingSlotList != null && parkingSlotList.size() > 0) {
            ParkingSlot parkingSlot = parkingSlotList.get(parkingSlotList.size() - 1);
            parkingSlot.setReserved(true);
            Car car = carService.findByPlateNumber(plate);
            parkingSlot.setCar(car);
            parkingSlotService.save(parkingSlot);
            return ResponseEntity.ok(new MessageDTO("OK"));
        } else return ResponseEntity.ok(new MessageDTO("Không thể gia hạn vì hết chỗ trống"));
    }
    // Quan end

    // Mai start

    /**
     * Display parking slot list
     *
     * @return parkingSlotList
     * Create by MaiHTQ
     */
    @GetMapping("/list")
    public ResponseEntity<List<ParkingSlotDTODisplay>> getListParkingSlot() {
        List<ParkingSlotDTODisplay> parkingSlotList = this.parkingSlotService.findAllDTO();
        return new ResponseEntity<>(parkingSlotList, HttpStatus.OK);
    }

    /**
     * Display slot type list
     *
     * @return slotTypeList
     * Create by MaiHTQ
     */
    @GetMapping("/slot-type")
    public ResponseEntity<List<SlotType>> getListSlotType() {
        List<SlotType> slotTypeList = this.parkingSlotService.findAllSlotType();
        return new ResponseEntity<>(slotTypeList, HttpStatus.OK);
    }

    /**
     * Create new position parking slot
     *
     * @param parkingSlotDTO
     * @return Create by MaiHTQ
     */
    @PostMapping("/create")
    public ResponseEntity<ParkingSlot> createNewParkingSlot(@RequestBody ParkingSlotDTO parkingSlotDTO) {
        this.parkingSlotService.save(parkingSlotDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Search floor
     *
     * @param floor
     * @return parkingSlotList
     * Create by MaiHTQ
     */
    @GetMapping("/search-floor/{floor}")
    public ResponseEntity<List<ParkingSlotDTODisplay>> searchFloorParkingLot(@PathVariable String floor) {
        List<ParkingSlotDTODisplay> parkingSlotList = this.parkingSlotService.findParkingSlotByFloor(floor);
        return new ResponseEntity<>(parkingSlotList, HttpStatus.OK);
    }

    /**
     * Validate exists slot number and floor
     *
     * @param slotNumber
     * @param floor
     * @return parkingSlot
     * Create by MaiHTQ
     */
    @GetMapping("/find-parking-slot-by-slot-number-floor/{slotNumber}/{floor}")
    public ResponseEntity<ParkingSlot> getParkingSlotById(@PathVariable String slotNumber, @PathVariable String floor) {
        ParkingSlot parkingSlot = this.parkingSlotService.findParkingSlotBySlotNumberAndFloor(slotNumber, floor);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }
    // Mai End
}
