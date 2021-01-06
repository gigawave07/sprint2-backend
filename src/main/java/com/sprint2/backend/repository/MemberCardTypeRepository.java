package com.sprint2.backend.repository;

import com.sprint2.backend.entity.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.MemberCardType;

@Repository
public interface MemberCardTypeRepository extends JpaRepository<MemberCardType, Long> {
    MemberCardType findMemberCardTypeByMemberTypeName (String string);
}
