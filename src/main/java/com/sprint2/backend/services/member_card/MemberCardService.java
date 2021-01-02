package com.sprint2.backend.services.member_card;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;

public interface MemberCardService {
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    void save(MemberCard memberCard);

    MemberCard findByPlateNumber(String plateNumber);

    MemberCard findByCustomerName(String fullName);

    List<MemberCard> findByCustomerID(Long id);
}
