package com.sprint2.backend.services.member_card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.repository.MemberCardRepository;

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
