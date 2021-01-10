package com.sprint2.backend.repository;


import com.sprint2.backend.entity.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface
MemberCardRepository extends JpaRepository<MemberCard, Long> {
}
