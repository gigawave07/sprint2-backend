package com.sprint2.backend.repository;

import com.sprint2.backend.entity.MemberCardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCardTypeRepository extends JpaRepository<MemberCardType, Long> {
}
