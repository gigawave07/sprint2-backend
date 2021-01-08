package com.sprint2.backend.services.lanh_nqn.member_card_type;


import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.repository.lanh_nqn.MemberCardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberCardTypeServiceImpl implements MemberCardTypeService {
    @Autowired
    private MemberCardTypeRepository memberCardTypeRepository;

    @Override
    public List<MemberCardType> findAll() {
        return this.memberCardTypeRepository.findAll();
    }
}
