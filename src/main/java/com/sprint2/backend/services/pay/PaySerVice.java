package com.sprint2.backend.services.pay;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;

public interface PaySerVice {
    List<MemberCard> findByCustomerID(Long id);

    void updateMemberCardAndSendMailAfterCustomerPay(Double money, List<Long> listIDMemberCard);
}
