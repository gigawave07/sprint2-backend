package com.sprint2.backend.services.member_card;
import com.sprint2.backend.entity.Car;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.repository.CarRepository;
import com.sprint2.backend.repository.MemberCardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.repository.MemberCardRepository;

//Ngan's tasks
@Service
public class MemberCardServiceImpl implements MemberCardService {
    @Autowired
    private MemberCardRepository memberCardRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
     private MemberCardTypeRepository memberCardTypeRepository;
    @Override
    public List<MemberCard> findAll() {
        return this.memberCardRepository.findAll();
    }

    @Override
    public MemberCard findByID(Long id) {
        return this.memberCardRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMemberCard (CustomerDTO customerDTO) {
        Car car = carRepository.findCarByPlateNumber(customerDTO.getPlateNumber());
        MemberCard memberCard = new MemberCard();
        memberCard.setCar(car);
        memberCard.setEndDate(customerDTO.getEndDate());
        memberCard.setStartDate(customerDTO.getStartDate());
        memberCard.setMemberCardType(this.memberCardTypeRepository.findById(customerDTO.getMemberCardType()).orElse(null));
        this.memberCardRepository.save(memberCard);
//        this.carRepository.save(car);
    }
    @Override
    public MemberCard findByPlateNumber(String plateNumber) {
        return this.memberCardRepository.findByCar_PlateNumber(plateNumber);
    }

    @Override
    public MemberCard findByCustomerName(String fullName) {
        return this.memberCardRepository.findByCar_Customer_FullName(fullName);
    }

    @Override
    public MemberCard findByCustomerMail(String mail) {
        return this.memberCardRepository.findByCar_Customer_Email(mail);
    }
}
//End Ngan's task
