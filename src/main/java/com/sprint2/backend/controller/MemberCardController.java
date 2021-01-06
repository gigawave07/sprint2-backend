package com.sprint2.backend.controller;

import com.sprint2.backend.converter.MemberCardExpiredConverter;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.MemberCardExpiredDTO;
import com.sprint2.backend.services.member_card.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/member-card")
@CrossOrigin
public class MemberCardController {
    @Autowired
    MemberCardService memberCardService;
    @Autowired
    MemberCardExpiredConverter memberCardExpiredConverter;

    @GetMapping(value = "/memberCardExpired")
    public List<MemberCardExpiredDTO> getMemberCardExpired() {
        MemberCardExpiredDTO memberCardExpiredDTO = null;
        List<MemberCardExpiredDTO> memberCardExpiredDTOList = new ArrayList<>() ;
        List<MemberCard> memberCardList = this.memberCardService.findAllMemberCardByEndDateExpires();
        for (int i = 0; i <memberCardList.size() ; i++) {
            memberCardExpiredDTO = this.memberCardExpiredConverter.toMemberCardExpiredDTO(memberCardList.get(i));
            memberCardExpiredDTOList.add(memberCardExpiredDTO);
        }
        return memberCardExpiredDTOList;
    }
    @CrossOrigin
    @GetMapping(value = "/detailCardExpired/{idMemberCard}")
    public MemberCardExpiredDTO getMemberCardById(@PathVariable("idMemberCard") Long id) {
        MemberCard memberCard = this.memberCardService.findByID(id);
        MemberCardExpiredDTO memberCardExpiredDTO = this.memberCardExpiredConverter.toMemberCardExpiredDTO(memberCard);
        return memberCardExpiredDTO;
    }

}
