package com.sprint2.backend.services.member_card;

import java.util.List;

import com.sprint2.backend.dto.MemberCardAddDTO;
import com.sprint2.backend.dto.MemberCardListDTO;
import com.sprint2.backend.entity.MemberCard;


public interface MemberCardService {
    List<MemberCardListDTO> findAllMemberCardDTO();

    MemberCard findByCustomerName(String fullName);

    void save(MemberCardAddDTO memberCardAddDTO);
}
