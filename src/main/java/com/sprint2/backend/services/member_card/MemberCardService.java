package com.sprint2.backend.services.member_card;

import java.util.List;

import com.sprint2.backend.dto.MemberCardAddDTO;
import com.sprint2.backend.dto.MemberCardListDTO;


public interface MemberCardService {
    List<MemberCardListDTO> findAllMemberCardDTO();

    void save(MemberCardAddDTO memberCardAddDTO);
}
