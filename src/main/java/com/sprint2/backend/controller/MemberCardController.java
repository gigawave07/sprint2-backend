package com.sprint2.backend.controller;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.member_card.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/member-card")
@CrossOrigin
public class MemberCardController {
    @Autowired
    private MemberCardService memberCardService;

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
}
