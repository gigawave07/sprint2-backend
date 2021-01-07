package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCard, Long> {
    MemberCard findByCar_PlateNumber(String plateNumber);

    @Query(value = "select * from member_card " +
            "inner join car " +
            "on member_card.car_id = car.id " +
            "inner join customer " +
            "on car.customer_id = customer.id " +
            "where customer_id = ?1", nativeQuery = true)
    List<MemberCard> findByCustomerId(Long id);

    MemberCard findByCar_Customer_FullName(String fullName);
}
