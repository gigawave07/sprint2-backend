package com.sprint2.backend.services.member_card;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.MemberCardAddDTO;
import com.sprint2.backend.model.MemberCardEditDTO;
import com.sprint2.backend.model.MemberCardListDTO;
import com.sprint2.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private AppRoleRepository appRoleRepository;

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Autowired
    private CarTypeRepository carTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private EntryLogRepository entryLogRepository;

    @Override
    public List<MemberCard> findAll() {
        return this.memberCardRepository.findAll();
    }

    @Override
    public MemberCard findByID(Long id) {
        return this.memberCardRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MemberCard memberCard) {
        this.memberCardRepository.save(memberCard);
    }

    @Override
    public List<MemberCard> findByPlateNumber(String plateNumber) {
        return this.memberCardRepository.findByCar_PlateNumber(plateNumber);
    }


    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê tổng số lượng vé mỗi loại theo tuần tháng năm
     */
    @Override
    public Object getTotalMemberCardType() {
        return this.memberCardRepository.getTotalMemberCardType();
    }


    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê doanh thu trong khoảng thời gian (member card)
     */
    @Override
    public Object getTotalRevenueMemberCardPeriod(String fromDay, String toDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localFromDay = LocalDate.parse(fromDay, formatter);
        LocalDate localToDay = LocalDate.parse(toDay, formatter);
        return this.memberCardRepository.getTotalRevenueMemberCardPeriod(localFromDay, localToDay);
    }


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 1 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek1(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek1(monthParam, yearParam);
    }

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 2 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek2(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek2(monthParam, yearParam);
    }

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 3 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek3(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek3(monthParam, yearParam);
    }

    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé tuần 4 (member card)
     */
    @Override
    public Object getTotalMemberCardWeek4(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardWeek4(monthParam, yearParam);
    }


    /**
     * @param monthParam
     * @param yearParam
     * @return Thống kê số vé theo tháng (member card)
     */
    @Override
    public Object getTotalMemberCardMonth(String monthParam, String yearParam) {
        return this.memberCardRepository.getTotalMemberCardMonth(monthParam, yearParam);
    }


    /**
     * Nguyen Quang Danh
     * End
     *
     * @param yearParam
     * @return Thống kê số vé theo năm (member card)
     */
    @Override
    public Object getTotalMemberCardYear(String yearParam) {
        return this.memberCardRepository.getTotalMemberCardYear(yearParam);
    }


    // ----------------------------- Vinh Begin ----------------------------------------

    @Override
    public List<MemberCard> findAllByCarId(Long carId) {
        return this.memberCardRepository.findAllByCarId(carId);
    }

    // ----------------------------- Vinh End ------------------------------------------

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
            memberCardListDTO.setSlotType(memberCard.getCar().getParkingSlot().getSlotType().getId());
            memberCardListDTOList.add(memberCardListDTO);
        }
        return memberCardListDTOList;
    }


    @Override
    public void saveDTO(MemberCardAddDTO memberCardAddDTO) {
        MemberCard memberCard = new MemberCard();
        Car car = new Car();
        Customer customer = new Customer();

        // create Account :
        AppAccount appAccount = new AppAccount();
        appAccount.setUsername("abc@abc.abc");
        appAccount.setPassword("$2a$10$BuU4GIP9cNBHWZVG6CiU.e3e84oYJsaWikgs.LuF0mxuIFw3AHTcm");
        appAccount.setAppRole(this.appRoleRepository.findById(3L).orElse(null));
        appAccount.setEnabled(true);
        this.appAccountRepository.save(appAccount);

        // create Customer :
        customer.setFullName(memberCardAddDTO.getFullName());
        customer.setAppAccount(appAccount);
        customer.setCreateDate(LocalDateTime.now());
        customer.setEmail((Math.random() * 1000) + "abc@abc.abc");
        customer.setGender(false);
        this.customerRepository.save(customer);

        // create Car :
        car.setPlateNumber(memberCardAddDTO.getPlateNumber());
        car.setCustomer(this.customerRepository.findByFullName(memberCardAddDTO.getFullName()));
        car.setCarType(this.carTypeRepository.findById(1L).orElse(null));
        car.setBrandName("Lamborghini");
        this.carRepository.save(car);

        // set SlotNumber :
        ParkingSlot parkingSlot;
        parkingSlot = this.parkingSlotRepository.findById(memberCardAddDTO.getSlotNumber()).orElse(null);
        if (parkingSlot != null) {
            parkingSlot.setStatus(false);
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


    @Override
    public List<Car> findPlateNumber(String plateNumber) {
        return this.carRepository.findCarByPlateNumber(plateNumber);
    }

    @Override
    public List<MemberCardType> findAllMemberCardType() {
        return this.memberCardTypeRepository.findAll();
    }

    @Override
    public List<ParkingSlot> findAllParkingSlotNeed() {
        List<ParkingSlot> parkingSlotListExists = this.parkingSlotRepository.findAll();
        List<ParkingSlot> parkingSlotListDisplay = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingSlotListExists) {
            if (!parkingSlot.getReserved() && !parkingSlot.getStatus()) {
                parkingSlotListDisplay.add(parkingSlot);
            }
        }
        return parkingSlotListDisplay;
    }

    /**
     * Hoat start
     */
    @Override
    public String deleteMemberCard(Long id) {
        MemberCard memberCard = findByID(id);

        // set Parking Slot
        ParkingSlot parkingSlot =
                this.parkingSlotRepository.findById(memberCard.getCar().getParkingSlot().getId()).orElse(null);
        if (parkingSlot != null) {
            parkingSlot.setCar(null);
            parkingSlot.setReserved(false);
            parkingSlot.setStatus(false);
            parkingSlotRepository.save(parkingSlot);

            List<EntryLog> entryLogList = entryLogRepository.findAllByMemberCardId(id);
            if (entryLogList != null && !entryLogList.isEmpty()) {
                EntryLog entryLog = entryLogList.get(entryLogList.size() - 1);
                entryLogRepository.delete(entryLog);
            }
        }

        // delete MemberCard
        memberCard.setCar(null);
        memberCard.setMemberCardType(null);
        try {
            this.memberCardRepository.delete(memberCard);
        } catch (RuntimeException runtime) {
            return "Failed";
        }
        return "Succeed";
    }

    @Override
    public String editTicket(MemberCardEditDTO memberCardEditDTO) {
        try {
            if (memberCardEditDTO != null) {
                MemberCard memberCard = findByID(memberCardEditDTO.getId());
                if (memberCard != null) {
                    memberCard.setPrice(memberCardEditDTO.getPrice());
                    memberCard.setEndDate(memberCardEditDTO.getEndDate());
                    memberCard.setStartDate(memberCardEditDTO.getStartDate());
                    memberCard.setMemberCardType(
                            this.memberCardTypeRepository.findById
                                    (memberCardEditDTO.getMemberCardType()).orElse(null));
                    ParkingSlot parkingSlotNew =
                            this.parkingSlotRepository.findById(memberCardEditDTO.getFloor()).orElse(null);
                    if (parkingSlotNew != null) {
                        parkingSlotNew.setCar(memberCard.getCar());
                        parkingSlotNew.setReserved(true);
                        parkingSlotNew.setStatus(false);
                        this.parkingSlotRepository.save(parkingSlotNew);
                    }
                    ParkingSlot parkingSlot = memberCard.getCar().getParkingSlot();
                    parkingSlot.setReserved(false);
                    parkingSlot.setCar(null);
                    this.parkingSlotRepository.save(parkingSlot);
                    memberCardRepository.save(memberCard);
                } else {
                    return "Not found";
                }
            } else {
                return "DTO null";
            }
        } catch (RuntimeException runtime) {
            return "Failed";
        }
        return "Succeed";
    }

    @Override
    public List<ParkingSlot> findParkingSlotEdit(Long slotType) {
        List<ParkingSlot> parkingSlotListEdit = new ArrayList<>();
        List<ParkingSlot> parkingSlotListExists = this.parkingSlotRepository.findAll();
        for (ParkingSlot parkingSlot : parkingSlotListExists) {
            if (!parkingSlot.getReserved() && parkingSlot.getSlotType().getId().equals(slotType)
                    && !parkingSlot.getStatus() && parkingSlot.getCar() == null) {
                parkingSlotListEdit.add(parkingSlot);
            }
        }
        return parkingSlotListEdit;
    }
}
