package com.sprint2.backend.repository;


import com.sprint2.backend.entity.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface
MemberCardRepository extends JpaRepository<MemberCard, Long> {
    /**
     * Lanh
     * @param plateNumber
     * @return MemberCard
     */
    MemberCard findByCar_PlateNumber(String plateNumber);
}
