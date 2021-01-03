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

//  Nhật kiểm tra xe có trong phải thành viên ko
    @Query(nativeQuery = true, value = "SELECT * \n" +
            "FROM member_card\n" +
            "where car_id = ?;")
    List<MemberCard> checkMemberCar(long idCar);

//  Nhật kiểm tra xe còn thời hạn ko
    @Query(nativeQuery = true, value = "SELECT * \n" +
            "FROM member_card\n" +
            "where timestampdiff(second,now(),end_date)> 0 and car_id = ?;")
    MemberCard getExpiryDateOfCar(long idCar);
}
