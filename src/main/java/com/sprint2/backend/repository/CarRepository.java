package com.sprint2.backend.repository;

import com.sprint2.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Thống kê số lượng xe của mỗi khách hàng
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'full_name', project2_parking_management.statistics_total_car_customer.full_name," +
            "'total_car_customer', project2_parking_management.statistics_total_car_customer.total_car_customer))" +
            "from project2_parking_management.statistics_total_car_customer;")
    Object getTotalCarOfCustomer();

}
