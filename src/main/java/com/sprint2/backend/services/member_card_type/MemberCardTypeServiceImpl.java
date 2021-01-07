package com.sprint2.backend.services.member_card_type;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.repository.CarRepository;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.repository.MemberCardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class MemberCardTypeServiceImpl implements MemberCardTypeService {
    //Ngan's tasks
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberCardTypeRepository memberCardTypeRepository;
    @Autowired
    MemberCardRepository memberCardRepository;
    @Override
    public List<MemberCardType> findAll() {
        return memberCardTypeRepository.findAll();
    }

    @Override
    public MemberCardType findMemberCardType(String string) {
        return memberCardTypeRepository.findMemberCardTypeByMemberTypeName(string);
    }

    @Override
    public MemberCardType findById(Long id) {
        return memberCardTypeRepository.findById(id).orElse(null);
    }
    //End Ngan's tasks
}
