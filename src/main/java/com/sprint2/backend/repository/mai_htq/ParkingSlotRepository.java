package com.sprint2.backend.repository;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.SlotType;
import com.sprint2.backend.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ParkingSlot;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    ParkingSlot findByFloorContaining(String floor);

    ParkingSlot findByReservedContaining(Boolean reserved);

//    Nhật đổi parking slot
    ParkingSlot getParkingSlotByCarId(long idCar);

    // Quan start
    ParkingSlot findByFloorAndSlotNumber(String floor, String slotNumber);

    List<ParkingSlot> findBySlotTypeAndStatusAndReserved(SlotType slotType, Boolean status, Boolean reserved);

    ParkingSlot findByCar(Car car);

    ParkingSlot findByCar_PlateNumber(String plateNumber);

    ParkingSlot findByCar_Id(Long id);

    List<ParkingSlot> findByFloor(String floor);

    List<ParkingSlot> findByFloorAndStatusAndReserved(String floor, Boolean status, Boolean reserved);

    @Query(value = "select floor from parking_slot group by floor", nativeQuery = true)
    List<String> getAllFloor();

    // Quan end


    /**
     * Nguyen Quang Danh
     * Begin
     *
     * @return Thống kê số lượng các hãng xe đang có tại bãi
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'total_car_type', project2_parking_management.statistics_brand_car.total_car_type," +
            "'brand_name', project2_parking_management.statistics_brand_car.brand_name))" +
            "from project2_parking_management.statistics_brand_car;")
    Object getTotalCarTypeParkingSlot();


    /**
     * @return Thống kê số lượng xe đang có tại bãi
     */
    @Query(nativeQuery = true, value = "select count(project2_parking_management.parking_slot.id) as total_car_parking from project2_parking_management.parking_slot\n" +
            "inner join project2_parking_management.car on parking_slot.car_id = car.id\n" +
            "where project2_parking_management.parking_slot.status = 1")
    Long getTotalCarParking();

    /**
     * Nguyen Quang Danh
     * End
     *
     * @return Thống kê tổng số lượng ví trí đỗ xe của bãi
     */
    @Query(nativeQuery = true, value = "select count(project2_parking_management.parking_slot.id) as total_parking_slot from project2_parking_management.parking_slot;")
    Long getTotalParkingSlot();

    // -------------------------------- Vinh Begin ------------------------------------------

    ParkingSlot findByCarIdAndStatusTrue(Long carId);
    // -------------------------------- Vinh End ------------------------------------------

    // mai start
    ParkingSlot findParkingSlotBySlotNumberAndFloor(String slot, String floor);
    // mai end

}
