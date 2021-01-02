package com.sprint2.backend.services.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.entity.Invoice;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.repository.InvoiceRepository;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service
public class PayServiceImpl implements PaySerVice {
    @Autowired
    private MemberCardRepository memberCardRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<MemberCard> findByCustomerID(Long id) {
//        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
//        sha256_HMAC.init(secret_key);
        return this.memberCardRepository.findByCar_Customer_Id(id);
    }

    @Override
    public void updateMemberCardAfterPay(Double money, List<Long> memberCardList) {
        Customer customer = null;
        Invoice invoice = new Invoice();
        for (Long element : memberCardList) {
            MemberCard memberCard = this.memberCardRepository.findById(element).orElse(null);
            if (memberCard != null) {
                if (memberCard.getMemberCardType().getMemberTypeName().equals("Tuần")) {
                    memberCard.setEndDate(memberCard.getEndDate().plusDays(7));
                } else if (memberCard.getMemberCardType().getMemberTypeName().equals("Tháng")) {
                    memberCard.setEndDate(memberCard.getEndDate().plusMonths(1));
                } else {
                    memberCard.setEndDate(memberCard.getEndDate().plusYears(1));
                }
                this.memberCardRepository.save(memberCard);
                if (customer == null) {
                    customer = memberCard.getCar().getCustomer();
                    invoice.setTotalAmount(money);
                    invoice.setCustomer(customer);
                    invoice.setPayDate(LocalDateTime.now());
                    this.invoiceRepository.save(invoice);
                }
            }
        }
    }
}
