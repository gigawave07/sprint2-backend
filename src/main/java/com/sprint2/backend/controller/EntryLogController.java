package com.sprint2.backend.controller;

import com.sprint2.backend.entity.EntryLog;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.entrylog.EntryLogService;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/entry-log")
@CrossOrigin
public class EntryLogController {
    @Autowired
    private EntryLogService entryLogService;

    @Autowired
    MemberCardService memberCardService;

    @Autowired
    ParkingSlotService parkingSlotService;

    // --------------------- VInh Begin ---------------------------
    @GetMapping("/check-entry-log/{check}/{memberCardId}")
    public ResponseEntity<?> checkEntryLog(@PathVariable Long memberCardId, @PathVariable Boolean check) {
        List<EntryLog> entryLogList = null;
        String result = "";
        LocalDateTime toDay = LocalDateTime.now();
        if (memberCardId != null) {
            entryLogList = this.entryLogService.findByMemberCardId(memberCardId);
            if (entryLogList.size() != 0){
                EntryLog entryLog = entryLogList.get(entryLogList.size() - 1);
                boolean isPark = entryLog.getExitDate() != null && entryLog.getExitDate().isBefore(toDay);
                if (isPark) {
                    if (check) {
                        result = setEntryLogIn(memberCardId);
                    } else {
                        result = "not park";
                    }
                } else if (entryLog.getExitDate() == null) {
                    if (!check) {
                        result = setEntryLogOut(memberCardId);
                    } else {
                        result = " parked";
                    }
                }
            } else {
                if (check) {
                    EntryLog entryLog = new EntryLog();
                    entryLog.setEnterDate(LocalDateTime.now());
                    MemberCard memberCard = this.memberCardService.findByID(memberCardId);
                    entryLog.setMemberCard(memberCard);
                    entryLog.getMemberCard().setId(memberCardId);
                    this.entryLogService.save(entryLog);
                    ParkingSlot parkingSlot = this.parkingSlotService.findByCar_Id(memberCard.getCar().getId());
                    parkingSlot.setStatus(true);
                    this.parkingSlotService.save(parkingSlot);
                    result = "park success";
                } else {
                    result = "not park";
                }
            }
        }
        return entryLogList != null ? ResponseEntity.ok(new MessageDTO(result)) : ResponseEntity.ok(new MessageDTO("not park"));
    }
    public String setEntryLogIn(Long memberCardId) {
        EntryLog entryLog = new EntryLog();
        if (memberCardId != null) {
            // set entry log
            entryLog.setEnterDate(LocalDateTime.now());
            MemberCard memberCard = this.memberCardService.findByID(memberCardId);
            entryLog.setMemberCard(memberCard);
            this.entryLogService.save(entryLog);
            // set parking slot
            ParkingSlot parkingSlot = this.parkingSlotService.findByCar_Id(memberCard.getCar().getId());
            parkingSlot.setStatus(true);
            this.parkingSlotService.save(parkingSlot);
        }
        return "park success";
    }
    public String setEntryLogOut(Long memberCardId) {
        EntryLog entryLog = new EntryLog();
        if (memberCardId != null) {
            // set entry log
            List<EntryLog> entryLogList = this.entryLogService.findByMemberCardId(memberCardId);
            MemberCard memberCard = this.memberCardService.findByID(memberCardId);
            entryLog = entryLogList.get(entryLogList.size() - 1);
            entryLog.setExitDate(LocalDateTime.now());
            this.entryLogService.save(entryLog);
            // set Parking slot
            ParkingSlot parkingSlot = this.parkingSlotService.findByCar_Id(memberCard.getCar().getId());
            parkingSlot.setStatus(false);
            this.parkingSlotService.save(parkingSlot);
        }
        return "car left";
    }
// --------------------- VInh End ---------------------------
}
