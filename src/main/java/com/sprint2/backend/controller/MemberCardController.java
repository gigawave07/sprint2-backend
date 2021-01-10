package com.sprint2.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sprint2.backend.model.MemberCardAddDTO;
import com.sprint2.backend.model.MemberCardListDTO;
import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.services.LanhService;

@RestController
@CrossOrigin
@RequestMapping("/lanh/member-card")
public class MemberCardController {
    @Autowired
    private LanhService lanhService;

    /**
     * Lành start
     */
    @PostMapping("/add")
    public ResponseEntity<?> addMemberCard(@RequestBody MemberCardAddDTO memberCardAddDTO) {
        this.lanhService.save(memberCardAddDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/list")
    public ResponseEntity<List<MemberCardListDTO>> getMemberCardListDemo() {
        List<MemberCardListDTO> memberCardListDTOList = this.lanhService.findAllMemberCardDTO();
        return ResponseEntity.ok(memberCardListDTOList);
    }

    @GetMapping("/find-plate-number/{plateNumber}")
    public ResponseEntity<List<Car>> getEmployeeByCode(@PathVariable String plateNumber) {
        List<Car> carList = this.lanhService.findPlateNumber(plateNumber);
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/parkingSlot")
    public ResponseEntity<List<ParkingSlot>> getParkingSlot() {
        List<ParkingSlot> parkingSlotList = this.lanhService.findAllParkingSlotNeed();
        return ResponseEntity.ok(parkingSlotList);
    }

    @GetMapping("/memberCardType")
    public ResponseEntity<List<MemberCardType>> getAll() {
        List<MemberCardType> memberCardTypeList = this.lanhService.findAllMemberCardType();
        return ResponseEntity.ok(memberCardTypeList);
    }

    @GetMapping("/search-plate-number/{plateNumber}")
    public ResponseEntity<List<MemberCardListDTO>> searchPlateNumber(@PathVariable String plateNumber) {
        List<MemberCardListDTO> memberCardListDTOS = this.lanhService.findByCarPlateNumber(plateNumber);
        return new ResponseEntity<>(memberCardListDTOS, HttpStatus.OK);
    }

    /**
     * Lành end
     */
}
