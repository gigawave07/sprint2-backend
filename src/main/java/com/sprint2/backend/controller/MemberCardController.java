package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.model.MemberCardAddDTO;
import com.sprint2.backend.model.MemberCardEditDTO;
import com.sprint2.backend.model.MemberCardListDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.services.member_card.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/member-card")
public class MemberCardController {
    @Autowired
    private MemberCardService memberCardService;

    @Autowired
    MemberCardRepository memberCardRepository;

    // ---------------------------- Vinh Begin ---------------------------------------
    @GetMapping("/get-member-card-id-by-car-id/{carId}")
    public ResponseEntity<?> getMemberCardIdByCarId(@PathVariable Long carId) {
        List<MemberCard> memberCardList;
        MemberCard memberCard = null;
        if (carId != null) {
            memberCardList = this.memberCardService.findAllByCarId(carId);
            if (memberCardList.size() != 0) {
                memberCard = memberCardList.get(memberCardList.size() - 1);
                if (memberCard.getEndDate().isBefore(LocalDateTime.now())) {
                    memberCard = null;
                }
            }
        }
        return memberCard != null ? ResponseEntity.ok(new MessageDTO(memberCard.getId().toString()))
                : ResponseEntity.ok(new MessageDTO("not found"));
    }
    // ---------------------------- Vinh Begin ---------------------------------------

    /**
     * Lành start
     */
    @PostMapping("/add")
    public ResponseEntity<?> addMemberCard(@RequestBody MemberCardAddDTO memberCardAddDTO) {
        this.memberCardService.saveDTO(memberCardAddDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<List<MemberCardListDTO>> getMemberCardListDemo() {
        List<MemberCardListDTO> memberCardListDTOList = this.memberCardService.findAllMemberCardDTO();
        return ResponseEntity.ok(memberCardListDTOList);
    }

    @GetMapping("/find-plate-number/{plateNumber}")
    public ResponseEntity<List<Car>> getEmployeeByCode(@PathVariable String plateNumber) {
        List<Car> carList = this.memberCardService.findPlateNumber(plateNumber);
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/parking-slot")
    public ResponseEntity<List<ParkingSlot>> getParkingSlot() {
        List<ParkingSlot> parkingSlotList = this.memberCardService.findAllParkingSlotNeed();
        return ResponseEntity.ok(parkingSlotList);
    }

    @GetMapping("/member-card-type")
    public ResponseEntity<List<MemberCardType>> getAll() {
        List<MemberCardType> memberCardTypeList = this.memberCardService.findAllMemberCardType();
        return ResponseEntity.ok(memberCardTypeList);
    }

    @GetMapping("/search-plate-number/{plateNumber}")
    public ResponseEntity<List<MemberCardListDTO>> searchPlateNumber(@PathVariable String plateNumber) {
        List<MemberCardListDTO> memberCardListDTOS = this.memberCardService.findByCarPlateNumber(plateNumber);
        return new ResponseEntity<>(memberCardListDTOS, HttpStatus.OK);
    }

    /*
     * Lành end
     */

    /**
     * Hoat start
     */
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<MemberCard> findByID(@PathVariable Long id) {
        MemberCard memberCard = this.memberCardService.findByID(id);
        return ResponseEntity.ok(memberCard);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDTO> deleteMemberCard(@PathVariable Long id) {
        String message = this.memberCardService.deleteMemberCard(id);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @PutMapping("/edit")
    public ResponseEntity<MessageDTO> editMemberCard(@RequestBody MemberCardEditDTO memberCardEditDTO) {
        String message = this.memberCardService.editTicket(memberCardEditDTO);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @GetMapping("/slot-type-edit/{slotType}")
    public ResponseEntity<List<ParkingSlot>> getParkingSlotEdit(@PathVariable Long slotType) {
        List<ParkingSlot> parkingSlotList = this.memberCardService.findParkingSlotEdit(slotType);
        return ResponseEntity.ok(parkingSlotList);
    }

    /*
     * Hoat end
     */

    // Quan start
    @GetMapping("/extend-duration/{id}")
    public ResponseEntity<?> extendDuration(@PathVariable Long id) {
        MemberCard memberCard = memberCardRepository.findById(id).orElse(null);
        memberCard.setStartDate(LocalDateTime.now());
        switch (memberCard.getMemberCardType().getMemberTypeName()) {
            case "Tuần":
                memberCard.setEndDate(memberCard.getStartDate().plusDays(7));
                memberCardRepository.save(memberCard);
                return ResponseEntity.ok(new MessageDTO("Updated week"));
            case "Tháng":
                memberCard.setEndDate(memberCard.getStartDate().plusMonths(1));
                memberCardRepository.save(memberCard);
                return ResponseEntity.ok(new MessageDTO("Updated month"));
            case "Năm":
                memberCard.setEndDate(memberCard.getStartDate().plusYears(1));
                memberCardRepository.save(memberCard);
                return ResponseEntity.ok(new MessageDTO("Updated year"));
            default:
                return ResponseEntity.ok(new MessageDTO("Updated failed"));
        }

    }
    // Quan end
}
