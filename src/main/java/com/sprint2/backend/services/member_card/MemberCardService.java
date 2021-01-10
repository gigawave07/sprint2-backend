package com.sprint2.backend.services.member_card;

import com.sprint2.backend.entity.MemberCard;

import java.util.List;

public interface MemberCardService {
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    void save(MemberCard memberCard);

    List<MemberCard> findByPlateNumber(String plateNumber);

    MemberCard findByCustomerName(String fullName);

    MemberCard findByCustomerMail(String mail);


    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
     */
    Object getTotalMemberCardType();


    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê doanh thu trong khoảng thời gian (member card)
     */
    Object getTotalRevenueMemberCardPeriod(String fromDay, String toDay);


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 1 (member card)
     */
    Object getTotalMemberCardWeek1(String monthParam, String yearParam);

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 2 (member card)
     */
    Object getTotalMemberCardWeek2(String monthParam, String yearParam);

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 3 (member card)
     */
    Object getTotalMemberCardWeek3(String monthParam, String yearParam);

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 4 (member card)
     */
    Object getTotalMemberCardWeek4(String monthParam, String yearParam);


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé theo tháng (member card)
     */
    Object getTotalMemberCardMonth(String monthParam, String yearParam);


    /**
     * Nguyen Quang Danh
     * End
     *
     * @param yearParam
     * @return Thống kê số vé theo năm (member card)
     */
    Object getTotalMemberCardYear(String yearParam);


    // ----------------------------- Vinh Begin ----------------------------------------
    List<MemberCard> findAllByCarId(Long carId);
    // ----------------------------- Vinh End ------------------------------------------
}
