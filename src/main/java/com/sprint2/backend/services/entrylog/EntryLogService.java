package com.sprint2.backend.services.entrylog;

import com.sprint2.backend.entity.EntryLog;

import java.util.List;

public interface EntryLogService {
    // --------------------- Vinh Begin ----------------------------
    List<EntryLog> findByMemberCardId(Long memberCardId);

    void save(EntryLog entryLog);
    // --------------------- Vinh End ----------------------------
}
