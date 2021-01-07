package com.sprint2.backend.services.member_card;

import com.sprint2.backend.dto.MemberCardAddDTO;
import com.sprint2.backend.dto.MemberCardListDTO;
import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.sprint2.backend.entity.MemberCard;


@Service
public class MemberCardServiceImpl implements MemberCardService {
    @Autowired
    private MemberCardRepository memberCardRepository;

    @Autowired
    private MemberCardTypeRepository memberCardTypeRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;


    @Override
    public List<MemberCard> findAll() {
        return this.memberCardRepository.findAll();
    }

    /**
     * Lành
     */
    @Override
    public List<MemberCardListDTO> findAllMemberCardDTO() {
        List<MemberCardListDTO> memberCardListDTOList = new ArrayList<>();

        List<MemberCard> memberCardList = this.memberCardRepository.findAll();
        for (MemberCard memberCard : memberCardList) {
            MemberCardListDTO memberCardListDTO = new MemberCardListDTO();
            memberCardListDTO.setId(memberCard.getId());
            memberCardListDTO.setStartDate(String.valueOf(memberCard.getStartDate()));
            memberCardListDTO.setEndDate(String.valueOf(memberCard.getEndDate()));
            memberCardListDTO.setPrice(memberCard.getPrice());
            memberCardListDTO.setNameType(memberCard.getMemberCardType().getMemberTypeName());
            memberCardListDTO.setPlateNumber(memberCard.getCar().getPlateNumber());
            memberCardListDTO.setNameCustomer(memberCard.getCar().getCustomer().getFullName());
            memberCardListDTO.setFloor(memberCard.getCar().getParkingSlot().getFloor());
            memberCardListDTO.setSlotNumber(memberCard.getCar().getParkingSlot().getSlotNumber());
            memberCardListDTOList.add(memberCardListDTO);
        }
        return memberCardListDTOList;
    }


    @Override
    public void save(MemberCardAddDTO memberCardAddDTO) {
        MemberCard memberCard = new MemberCard();
        Car car = new Car();
        Customer customer = new Customer();

        // create Customer :
        customer.setFullName(memberCardAddDTO.getFullName());
        this.customerRepository.save(customer);

        // create Car :
        car.setPlateNumber(memberCardAddDTO.getPlateNumber());
        car.setCustomer(this.customerRepository.findByFullName(memberCardAddDTO.getFullName()));
        this.carRepository.save(car);

        // set SlotNumber :
        ParkingSlot parkingSlot;
        parkingSlot = this.parkingSlotRepository.findById(memberCardAddDTO.getSlotNumber()).orElse(null);
        if (parkingSlot != null) {
            parkingSlot.setStatus(true);
            parkingSlot.setReserved(true);
            parkingSlot.setCar(car);
            this.parkingSlotRepository.save(parkingSlot);
        }

        // set properties of memberCard :
        memberCard.setMemberCardType(
                this.memberCardTypeRepository.findById(memberCardAddDTO.getMemberTypeID()).orElse(null));
        memberCard.setStartDate(memberCardAddDTO.getStartDate());
        memberCard.setEndDate(memberCardAddDTO.getEndDate());
        memberCard.setPrice(memberCardAddDTO.getPrice());
        memberCard.setCar(this.carRepository.findByPlateNumber(memberCardAddDTO.getPlateNumber()));
        this.memberCardRepository.save(memberCard);
    }

    @Override
    public MemberCard findByCustomerName(String fullName) {
        return this.memberCardRepository.findByCar_Customer_FullName(fullName);
    }
}
