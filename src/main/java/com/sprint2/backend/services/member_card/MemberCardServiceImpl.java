package com.sprint2.backend.services.member_card;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.model.MemberCardDTO;
import com.sprint2.backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.repository.MemberCardRepository;

@Service
public class MemberCardServiceImpl implements MemberCardService {
    @Autowired
    private MemberCardRepository memberCardRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<MemberCard> findAll() {
        return this.memberCardRepository.findAll();
    }

    @Override
    public MemberCard findByID(Long id) {
        return this.memberCardRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMemberCard (CustomerDTO customerDTO) {
        Car car = carRepository.findCarById(customerDTO.getCar_id());
        MemberCard memberCard = new MemberCard();
        memberCard.setEndDate(customerDTO.getEndDate());
        memberCard.setStartDate(customerDTO.getStartDate());
        memberCard.setMemberCardType(customerDTO.getMemberCardType());
        memberCard.setCar(car);
        this.memberCardRepository.save(memberCard);
    }
    @Override
    public MemberCard findByPlateNumber(String plateNumber) {
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
}
