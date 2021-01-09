package com.sprint2.backend.services.parking_slot;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.entity.SlotType;

import java.util.List;

public interface ParkingSlotService {
    List<ParkingSlot> findAll();

    ParkingSlot findByID(Long id);

    void save(ParkingSlot parkingSlot);

    ParkingSlot findByReserved(Boolean reserved);

    // Quan start

    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);

    ParkingSlot arrangeSlot(Car car);

    List<ParkingSlot> findBySlotTypeAndStatusAndReserved(SlotType slotType, Boolean status, Boolean reserved);

    ParkingSlot findByCar(Car car);

    Boolean parkRegisteredCar(Car car);

    Boolean checkoutRegisteredCar(Car car);

    ParkingSlot findByCar_PlateNumber(String plateNumber);
    ParkingSlot findByCar_Id(Long id);

    List<ParkingSlot> findByFloor(String floor);
    List<String> getAllFloor();
    List<ParkingSlot> findByFloorAndStatusAndReserved(String floor, Boolean status, Boolean reserved);

    // Quan end

    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê số lượng các hãng xe đang có tại bãi
     */
    Object getTotalCarTypeParkingSlot();


    /**
     * @return Thống kê số lượng xe đang có tại bãi
     */
    Long getTotalCarParking();


    /**
     * End
     *
     * @return Thống kê tổng số lượng vị trí đỗ xe của bãi
     */
    Long getTotalParkingSlot();
}
