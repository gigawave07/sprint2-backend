package com.sprint2.backend.services.member_card;

import com.sprint2.backend.entity.MemberCard;

import java.util.List;

public interface MemberCardService {
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    void save(MemberCard memberCard);

    MemberCard findByPlateNumber(String plateNumber);

    MemberCard findByCustomerName(String fullName);

    MemberCard findByCustomerMail(String mail);

    // Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
    Object getTotalMemberCardType();

    // Thống kê doanh thu trong khoảng thời gian (member card)
    Object getTotalRevenueMemberCardPeriod(String fromDay, String toDay);

    //     Thống kê số vé theo tuần (member card)
    Object getTotalMemberCardWeek(String monthParam, String yearParam);

    //     Thống kê số vé theo tháng (member card)
    Object getTotalMemberCardMonth(String monthParam, String yearParam);

    //     Thống kê số vé theo năm (member card)
    Object getTotalMemberCardYear(String yearParam);

    // Thống kê số vé theo tuần tháng năm
//     Object getTotalMemberCard(String monthParam, String yearParam);
}
