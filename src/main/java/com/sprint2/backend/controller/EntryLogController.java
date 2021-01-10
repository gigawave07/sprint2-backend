package com.sprint2.backend.controller;

import com.sprint2.backend.entity.EntryLog;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.entrylog.EntryLogService;
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

    // --------------------- VInh Begin ---------------------------

    @GetMapping("/check-entry-log/{memberCardId}")
    public ResponseEntity<?> checkEntryLog(@PathVariable Long memberCardId) {
        List<EntryLog> entryLogList = null;
        String result = "";
        LocalDateTime toDay = LocalDateTime.now();
        if (memberCardId != null) {
            entryLogList = this.entryLogService.findByMemberCardId(memberCardId);
        }
        EntryLog entryLog = entryLogList.get(entryLogList.size() - 1);
        boolean isPark = entryLog.getExitDate() != null && entryLog.getExitDate().isBefore(toDay);
        if (isPark) {
            result = "not park";
        } else if (entryLog.getExitDate() == null) {
            result = " parked";
        } else {
        }
        return entryLogList != null ? ResponseEntity.ok(new MessageDTO(result)) : ResponseEntity.ok(new MessageDTO("not park"));
    }

    // --------------------- VInh End ---------------------------
}
