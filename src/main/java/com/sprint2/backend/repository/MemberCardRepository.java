package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.MemberCard;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCard, Long> {
    MemberCard findByCar_PlateNumber(String plateNumber);

    MemberCard findByCar_Customer_Email(String mail);

    MemberCard findByCar_Customer_FullName(String fullName);

    // Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object('member_type_name',project2_parking_management.statistics_member_card_type.member_type_name, 'total_member_card_type',project2_parking_management.statistics_member_card_type.total_member_card_type)) from project2_parking_management.statistics_member_card_type;")
    Object getTotalMemberCardType();
}
