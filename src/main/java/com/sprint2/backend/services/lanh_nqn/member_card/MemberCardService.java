package com.sprint2.backend.services.lanh_nqn.member_card;

import com.sprint2.backend.model.MemberCardAddDTO;
import com.sprint2.backend.model.MemberCardListDTO;

import java.util.List;


public interface MemberCardService {
    List<MemberCardListDTO> findAllMemberCardDTO();

    void save(MemberCardAddDTO memberCardAddDTO);

    List<MemberCardListDTO> findByCarPlateNumber(String plateNumber);
}
