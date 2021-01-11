package com.sprint2.backend.services;

import com.sprint2.backend.model.MemberCardEditDTO;
import com.sprint2.backend.repository.MemberCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;

@Service
public class HoatServiceImpl implements HoatService {
    @Autowired
    private MemberCardRepository memberCardRepository;

    @Override
    public List<MemberCard> findAll() {
        return this.memberCardRepository.findAll();
    }

    @Override
    public MemberCard findByID(Long id) {
        return this.memberCardRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteMemberCard(Long id) {
        MemberCard memberCard = findByID(id);
        memberCard.setCar(null);
        memberCard.setEntryLogList(null);
        memberCard.setMemberCardType(null);
        try {
            this.memberCardRepository.deleteById(id);
        } catch (RuntimeException runtime) {
            return "Failed";
        }
        return "Succeed";
    }

    @Override
    public String editTicket(MemberCardEditDTO memberCardEditDTO) {
        try {
            if (memberCardEditDTO != null) {
                MemberCard memberCard = findByID(memberCardEditDTO.getId());
                if (memberCard != null) {
                    memberCard.setPrice(memberCardEditDTO.getPrice());
                    memberCard.setEndDate(memberCardEditDTO.getEndDate());
                    memberCard.setStartDate(memberCardEditDTO.getStartDate());
                    memberCard.getMemberCardType().setMemberTypeName(memberCardEditDTO.getMemberCardType());
                    memberCard.getCar().getParkingSlot().setFloor(memberCardEditDTO.getFloor());
                    memberCardRepository.save(memberCard);
                }else {
                    return "Not found";
                }
            } else {
                return "DTO null";
            }
        } catch (RuntimeException runtime) {
            return "Failed";
        }
        return "Succeed";
    }
}
