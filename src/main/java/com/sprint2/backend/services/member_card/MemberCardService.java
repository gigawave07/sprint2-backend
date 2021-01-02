package com.sprint2.backend.services.member_card;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import org.springframework.data.jpa.repository.Query;

public interface MemberCardService {
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    void save(MemberCard memberCard);

    MemberCard findByPlateNumber(String plateNumber);

    MemberCard findByCustomerName(String fullName);

    MemberCard findByCustomerMail(String mail);

    // Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
    Object getTotalMemberCardType();
}
