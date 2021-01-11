package com.sprint2.backend.services;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.MemberCardEditDTO;

public interface HoatService {
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    String  deleteMemberCard(Long id);

    String editTicket(MemberCardEditDTO memberCardEditDTO);
}
