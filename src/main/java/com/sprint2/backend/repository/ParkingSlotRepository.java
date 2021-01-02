package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.ParkingSlot;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    ParkingSlot findByFloorContaining(String floor);

    ParkingSlot findByReservedContaining(Boolean reserved);

    // Thống kê số lượng các hãng xe đang có tại bãi
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'total_car_type', project2_parking_management.statistics_brand_car.total_car_type," +
            "'brand_name', project2_parking_management.statistics_brand_car.brand_name))" +
            "from project2_parking_management.statistics_brand_car;")
    Object getTotalCarTypeParkingSlot();

    // Thống kê số lượng xe đang có tại bãi
    @Query(nativeQuery = true, value = "select count(project2_parking_management.parking_slot.id) as total_car_parking from project2_parking_management.parking_slot\n" +
            "inner join project2_parking_management.car on parking_slot.car_id = car.id\n" +
            "where project2_parking_management.parking_slot.status = 1")
    Long getTotalCarParking();

    // Thống kê tổng số lượng ví trí đỗ xe của bãi
    @Query(nativeQuery = true, value = "select count(project2_parking_management.parking_slot.id) as total_parking_slot from project2_parking_management.parking_slot;")
    Long getTotalParkingSlot();
}
