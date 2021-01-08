package com.sprint2.backend.services.lanh_nqn.member_card;

import com.sprint2.backend.model.MemberCardAddDTO;
import com.sprint2.backend.model.MemberCardListDTO;
import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.repository.lanh_nqn.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    public List<MemberCardListDTO> findByCarPlateNumber(String plateNumber) {
        List<MemberCardListDTO> memberCardListDTOList = new ArrayList<>();
        List<MemberCard> memberCardList = this.memberCardRepository.findAll();
        for (MemberCard memberCard : memberCardList) {
            if (memberCard.getCar().getPlateNumber().equals(plateNumber)) {
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
        }
        return memberCardListDTOList;
    }
}