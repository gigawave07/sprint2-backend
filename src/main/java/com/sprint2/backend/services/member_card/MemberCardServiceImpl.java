package com.sprint2.backend.services.member_card;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.repository.MemberCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MemberCardServiceImpl implements MemberCardService {
    @Autowired
    private MemberCardRepository memberCardRepository;

    @Override
    public List<MemberCard> findAll() {
        return this.memberCardRepository.findAll();
    }

    @Override
    public MemberCard findByID(Long id) {
        return this.memberCardRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MemberCard memberCard) {
        this.memberCardRepository.save(memberCard);
    }

    @Override
    public List<MemberCard> findByPlateNumber(String plateNumber) {
        return this.memberCardRepository.findByCar_PlateNumber(plateNumber);
    }

    @Override
    public MemberCard findByCustomerName(String fullName) {
        return this.memberCardRepository.findByCar_Customer_FullName(fullName);
    }

    @Override
    public MemberCard findByCustomerMail(String mail) {
        return this.memberCardRepository.findByCar_Customer_Email(mail);
    }


    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
     */
    @Override
    public Object getTotalMemberCardType() {
        return this.memberCardRepository.getTotalMemberCardType();
    }


    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê doanh thu trong khoảng thời gian (member card)
     */
    @Override
    public Object getTotalRevenueMemberCardPeriod(String fromDay, String toDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localFromDay = LocalDate.parse(fromDay, formatter);
        LocalDate localToDay = LocalDate.parse(toDay, formatter);
        return this.memberCardRepository.getTotalRevenueMemberCardPeriod(localFromDay, localToDay);
    }


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 1 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek1(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek1(monthParam, yearParam);
    }

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 2 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek2(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek2(monthParam, yearParam);
    }

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 3 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek3(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek3(monthParam, yearParam);
    }

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 4 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek4(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek4(monthParam, yearParam);
    }


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé theo tháng (member card)
     */
    @Override
    public Object getTotalMemberCardMonth(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardMonth(monthParam, yearParam);
    }


    /**
     * Nguyen Quang Danh
     * End
     *
     * @param yearParam
     * @return Thống kê số vé theo năm (member card)
     */
    @Override
    public Object getTotalMemberCardYear(String yearParam) {
        return this.memberCardRepository.getTotalMemberCardYear(yearParam);
    }


    // ----------------------------- Vinh Begin ----------------------------------------

    @Override
    public List<MemberCard> findAllByCarId(Long carId) {
        return this.memberCardRepository.findAllByCarId(carId);
    }

    // ----------------------------- Vinh End ------------------------------------------
}
