package com.sprint2.backend.controller;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.MemberCardEditDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.HoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/hoat")
@CrossOrigin
public class HoatController {
    @Autowired
    private HoatService memberCardService;

    @GetMapping("/list")
    public ResponseEntity<List<MemberCard>> getListMemberCard() {
        List<MemberCard> memberCardList = memberCardService.findAll();
        return new ResponseEntity<>(memberCardList, HttpStatus.OK);
    }

    @GetMapping("/findByMemberCardById/{id}")
    public ResponseEntity<MemberCard> getMemberCardById(@PathVariable Long id) {
        MemberCard memberCard = memberCardService.findByID(id);
        return new ResponseEntity<>(memberCard, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMemberCard/{id}")
    public ResponseEntity<MessageDTO> deleteMemberCard(@PathVariable Long id) {
        String message = this.memberCardService.deleteMemberCard(id);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @PutMapping("/edit")
    public ResponseEntity<MessageDTO> editMemberCard(@RequestBody MemberCardEditDTO memberCardEditDTO) {
        String message = this.memberCardService.editTicket(memberCardEditDTO);
        return ResponseEntity.ok(new MessageDTO(message));
    }
}
