package com.sprint2.backend.repository;

import com.sprint2.backend.entity.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCard, Long> {
    MemberCard findByCar_PlateNumber(String plateNumber);

    MemberCard findByCar_Customer_Email(String mail);

    MemberCard findByCar_Customer_FullName(String fullName);


    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê tổng số lượng vé mỗi loại tuần tháng năm
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object" +
            "('member_type_name',project2_parking_management.statistics_member_card_type.member_type_name," +
            "'total_member_card_type',project2_parking_management.statistics_member_card_type.total_member_card_type))" +
            "from project2_parking_management.statistics_member_card_type;")
    Object getTotalMemberCardType();

    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê doanh thu trong khoảng thời gian (member card)
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object" +
            "('date_payment', project2_parking_management.statistic_total_revenue_member_card.date_payment,\n" +
            "'total_price', project2_parking_management.statistic_total_revenue_member_card.total_price))\n" +
            "from project2_parking_management.statistic_total_revenue_member_card\n" +
            "where project2_parking_management.statistic_total_revenue_member_card.date_payment between (?1) and (?2);")
    Object getTotalRevenueMemberCardPeriod(LocalDate fromDay, LocalDate toDay);


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 1 (member card)
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'date', project2_parking_management.statistic_member_card_time.date,\n" +
            "'total_member_card', project2_parking_management.statistic_member_card_time.total_member_card_day))\n" +
            "from project2_parking_management.statistic_member_card_time\n" +
            "where (dayofmonth(project2_parking_management.statistic_member_card_time.date) between '01' and '07'\n" +
            "    and month(project2_parking_management.statistic_member_card_time.date) = :monthParam \n" +
            "    and year(project2_parking_management.statistic_member_card_time.date) = :yearParam )")
    Object getTotalMemberCardWeek1(String monthParam, String yearParam);

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 2 (member card)
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'date', project2_parking_management.statistic_member_card_time.date,\n" +
            "'total_member_card', project2_parking_management.statistic_member_card_time.total_member_card_day))\n" +
            "from project2_parking_management.statistic_member_card_time\n" +
            "where (dayofmonth(project2_parking_management.statistic_member_card_time.date) between '08' and '14'\n" +
            "    and month(project2_parking_management.statistic_member_card_time.date) = :monthParam \n" +
            "    and year(project2_parking_management.statistic_member_card_time.date) = :yearParam)")
    Object getTotalMemberCardWeek2(String monthParam, String yearParam);

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 3 (member card)
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'date', project2_parking_management.statistic_member_card_time.date,\n" +
            "'total_member_card', project2_parking_management.statistic_member_card_time.total_member_card_day))\n" +
            "from project2_parking_management.statistic_member_card_time\n" +
            "where (dayofmonth(project2_parking_management.statistic_member_card_time.date) between '15' and '21'\n" +
            "    and month(project2_parking_management.statistic_member_card_time.date) = :monthParam \n" +
            "    and year(project2_parking_management.statistic_member_card_time.date) = :yearParam)")
    Object getTotalMemberCardWeek3(String monthParam, String yearParam);

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 4 (member card)
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'date', project2_parking_management.statistic_member_card_time.date,\n" +
            "'total_member_card', project2_parking_management.statistic_member_card_time.total_member_card_day))\n" +
            "from project2_parking_management.statistic_member_card_time\n" +
            "where (dayofmonth(project2_parking_management.statistic_member_card_time.date) between '22' and '31'\n" +
            "    and month(project2_parking_management.statistic_member_card_time.date) = :monthParam \n" +
            "    and year(project2_parking_management.statistic_member_card_time.date) = :yearParam)")
    Object getTotalMemberCardWeek4(String monthParam, String yearParam);


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé theo tháng (member card)
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'date', project2_parking_management.statistic_member_card_time.date,\n" +
            "'total_member_card', project2_parking_management.statistic_member_card_time.total_member_card_day))\n" +
            "from project2_parking_management.statistic_member_card_time\n" +
            "where (month(project2_parking_management.statistic_member_card_time.date) = :monthParam\n" +
            "    and year(project2_parking_management.statistic_member_card_time.date) = :yearParam)")
    Object getTotalMemberCardMonth(String monthParam, String yearParam);


    /**
     * Nguyen Quang Danh
     * End
     *
     * @param yearParam
     * @return Thống kê số vé theo năm (member card)
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'date', project2_parking_management.statistic_member_card_time.date,\n" +
            "'total_member_card', project2_parking_management.statistic_member_card_time.total_member_card_day))\n" +
            "from project2_parking_management.statistic_member_card_time " +
            "where year(project2_parking_management.statistic_member_card_time.date) = :yearParam")
    Object getTotalMemberCardYear(String yearParam);

}
