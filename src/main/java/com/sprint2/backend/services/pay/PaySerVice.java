package com.sprint2.backend.services.pay;

import java.util.List;

import com.sprint2.backend.entity.MemberCard;

public interface PaySerVice {
    List<MemberCard> findByCustomerID(Long id);

    void updateMemberCardAfterPay(Double money, List<Long> memberCardList);

    String createSignature(String money);
}
