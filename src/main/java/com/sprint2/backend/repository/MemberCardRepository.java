package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.MemberCard;

@Repository
public interface
MemberCardRepository extends JpaRepository<MemberCard, Long>, JpaSpecificationExecutor<MemberCard> {
}
