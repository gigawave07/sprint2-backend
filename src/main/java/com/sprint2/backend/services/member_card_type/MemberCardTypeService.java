package com.sprint2.backend.services.member_card_type;

import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.model.CustomerDTO;

import java.util.List;

public interface MemberCardTypeService {
    //Ngan's tasks
    public List<MemberCardType> findAll();
    public MemberCardType findMemberCardType (String string);
    public MemberCardType findById(Long id);
    //End Ngan's tasks
}
