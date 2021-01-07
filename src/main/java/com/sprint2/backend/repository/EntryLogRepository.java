package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.EntryLog;

import java.util.List;

@Repository
public interface EntryLogRepository extends JpaRepository<EntryLog, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM entry_log\n" +
            "where member_card_id = ?;")
    List<EntryLog> getEntryLogByMemberCard(Long idMember);
}
