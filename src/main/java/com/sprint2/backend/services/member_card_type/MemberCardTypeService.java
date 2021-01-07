package com.sprint2.backend.services.member_card_type;

import com.sprint2.backend.entity.MemberCardType;

import java.util.List;

public interface MemberCardTypeService {
    List<MemberCardType> findAll();

    MemberCardType findByMemberTypeName(String memberTypeName);
}
