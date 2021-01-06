package com.sprint2.backend.converter;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.MemberCardExpiredDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberCardExpiredConverter {
    public MemberCardExpiredDTO toMemberCardExpiredDTO(MemberCard memberCard){
        MemberCardExpiredDTO memberCardExpiredDTO = new MemberCardExpiredDTO();
        memberCardExpiredDTO.setId(memberCard.getId());
        memberCardExpiredDTO.setEndDate(memberCard.getEndDate());
        memberCardExpiredDTO.setBrandName(memberCard.getCar().getBrandName());
        memberCardExpiredDTO.setBirthday(memberCard.getCar().getCustomer().getBirthday());
        memberCardExpiredDTO.setEmail(memberCard.getCar().getCustomer().getEmail());
        memberCardExpiredDTO.setFullName(memberCard.getCar().getCustomer().getFullName());
        memberCardExpiredDTO.setPlateNumber(memberCard.getCar().getPlateNumber());
        memberCardExpiredDTO.setPhone(memberCard.getCar().getCustomer().getPhone());
        memberCardExpiredDTO.setFloor(memberCard.getCar().getParkingSlot().getFloor());
        memberCardExpiredDTO.setSlotNumber(memberCard.getCar().getParkingSlot().getSlotNumber());
        return memberCardExpiredDTO;
    }
}
