package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.EntryLog;

@Repository
public interface EntryLogRepository extends JpaRepository<EntryLog, Long> {
}
