package com.sprint2.backend.services.member_card_type;

import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.model.CustomerDTO;

import java.util.List;

public interface MemberCardTypeService {
    public List<MemberCardType> findAll();
    public MemberCardType findMemberCardType (String string);
}
