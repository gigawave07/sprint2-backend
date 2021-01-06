package com.sprint2.backend.services.member_card;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.CarDTO;
import com.sprint2.backend.model.CustomerDTO;

public interface MemberCardService {
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    void saveMemberCard(CustomerDTO customerDTO);

    MemberCard findByPlateNumber(String plateNumber);

    MemberCard findByCustomerName(String fullName);

    MemberCard findByCustomerMail(String mail);
}
