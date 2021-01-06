package com.sprint2.backend.controller.chau_lp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.services.pay.PaySerVice;
import com.sprint2.backend.model.MessageDTO;

@RestController
@RequestMapping("/chau")
@CrossOrigin
public class PayRestController {
    @Autowired
    private PaySerVice paySerVice;

    /*
     * get all member card of customer currently logged in database
     * @param idCustomer
     * @return ResponseEntity<List<MemberCard>>
     * */
    @GetMapping("/list/{id}")
    public ResponseEntity<List<MemberCard>> getListMemberCardByIDCustomer(@PathVariable Long id) {
        List<MemberCard> memberCardList = this.paySerVice.findByCustomerID(id);
        return new ResponseEntity<>(memberCardList, HttpStatus.OK);
    }

    /*
     * update member card after customer pay
     * @param money, memberCardList
     * void
     * */
    @GetMapping("/pay/{money}/{memberCardList}")
    public void updateMemberCardAfterPay(@PathVariable Double money, @PathVariable List<Long> memberCardList) {
        this.paySerVice.updateMemberCardAfterPay(money, memberCardList);
    }

    /*
     * create signature for MoMo
     * @param money, requestID
     * @return MessageDTO
     * */
    @GetMapping("/create-signature/{money}")
    public ResponseEntity<MessageDTO> createSignatureForMoMo(@PathVariable String money) {
        MessageDTO messageDTO = new MessageDTO(this.paySerVice.createSignature(money));
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }
}
