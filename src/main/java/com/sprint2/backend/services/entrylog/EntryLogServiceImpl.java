package com.sprint2.backend.services.entrylog;

import com.sprint2.backend.entity.EntryLog;
import com.sprint2.backend.repository.EntryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryLogServiceImpl implements EntryLogService{
    @Autowired
    private EntryLogRepository entryLogRepository;

    // ------------------------ VInh Begin -----------------------------
    @Override
    public List<EntryLog> findByMemberCardId(Long memberCardId) {
        return this.entryLogRepository.findAllByMemberCardId(memberCardId);
    }
    // ------------------------ VInh Begin -----------------------------
}
