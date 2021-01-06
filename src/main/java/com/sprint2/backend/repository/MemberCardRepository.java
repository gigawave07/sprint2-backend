package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCard, Long> {
    MemberCard findByCar_PlateNumber(String plateNumber);

    List<MemberCard> findByCar_Customer_Id(Long id);

    MemberCard findByCar_Customer_FullName(String fullName);
}
