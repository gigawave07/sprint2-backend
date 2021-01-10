package com.sprint2.backend.services;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.model.MemberCardAddDTO;
import com.sprint2.backend.model.MemberCardListDTO;

public interface LanhService {
    List<MemberCardListDTO> findAllMemberCardDTO();

    void save(MemberCardAddDTO memberCardAddDTO);

    List<MemberCardListDTO> findByCarPlateNumber(String plateNumber);

    List<Car> findAllCar();

    List<Car> findPlateNumber(String plateNumber);

    List<MemberCardType> findAllMemberCardType();

    List<ParkingSlot> findAllParkingSlot();

    List<ParkingSlot> findAllParkingSlotNeed();
}
