package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.MemberCard;

import java.util.List;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCard, Long> {
    MemberCard findByCar_PlateNumber(String plateNumber);

    MemberCard findByCar_Customer_Email(String mail);

    MemberCard findByCar_Customer_FullName(String fullName);

    @Query(value="select * from project2_parking_management.member_card where (member_card.end_date < curdate())", nativeQuery = true)
    List<MemberCard> findAllMemberCardByEndDateExpires();

}
