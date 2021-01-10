package com.sprint2.backend.services.ngan_member_card;
import java.util.List;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.model.NganCustomerDTO;

public interface MemberCardService {
    //Ngan's tasks
    List<MemberCard> findAll();

    MemberCard findByID(Long id);

    void saveMemberCard(NganCustomerDTO nganCustomerDTO);

    MemberCard findByPlateNumber(String plateNumber);

    MemberCard findByCustomerName(String fullName);

    MemberCard findByCustomerMail(String mail);
    //End Ngan's tasks
}
