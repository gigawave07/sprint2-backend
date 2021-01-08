package com.sprint2.backend.repository;

import com.sprint2.backend.entity.EntryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryLogRepository extends JpaRepository<EntryLog, Long> {
}
