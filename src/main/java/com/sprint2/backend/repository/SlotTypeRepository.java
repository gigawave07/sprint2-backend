package com.sprint2.backend.repository;

import com.sprint2.backend.entity.SlotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotTypeRepository extends JpaRepository<SlotType, Long> {
}
