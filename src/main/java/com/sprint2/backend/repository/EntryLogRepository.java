package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.EntryLog;

import java.util.List;

@Repository
public interface EntryLogRepository extends JpaRepository<EntryLog, Long> {
    // ------------------------ Vinh Begin ---------------------------------
    List<EntryLog> findAllByMemberCardId(Long memberCardId);
    // ------------------------ Vinh End ---------------------------------
}
