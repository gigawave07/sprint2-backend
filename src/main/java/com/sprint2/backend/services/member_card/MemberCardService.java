package com.sprint2.backend.services.member_card;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.model.MemberCardAddDTO;
import com.sprint2.backend.model.MemberCardEditDTO;
import com.sprint2.backend.model.MemberCardListDTO;

public interface MemberCardService {
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    void save(MemberCard memberCard);

    List<MemberCard> findByPlateNumber(String plateNumber);


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

    /**
     * Lanh start
     */
    List<MemberCardListDTO> findAllMemberCardDTO();

    void saveDTO(MemberCardAddDTO memberCardAddDTO);

    List<MemberCardListDTO> findByCarPlateNumber(String plateNumber);

    List<Car> findPlateNumber(String plateNumber);

    List<MemberCardType> findAllMemberCardType();

    List<ParkingSlot> findAllParkingSlotNeed();

    /**
     * Hoat start
     */
    String  deleteMemberCard(Long id);

    String editTicket(MemberCardEditDTO memberCardEditDTO);

    List<ParkingSlot> findParkingSlotEdit(Long slotType);
}
