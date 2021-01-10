package com.sprint2.backend.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sprint2.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
//    Nhật Kiểm tra xe có trong db ko
    Car getCarByPlateNumber(String plateNumber);
    // Quan start
    Car findByPlateNumber(String plateNumber);
    // Quan end

    /**
     * nguyen quoc khanh
     * @param id
     * @param pageable
     * @return
     */
    @Query(value = "select * from car where customer_id= ?", nativeQuery = true)
    Page<Car> findAllCarByCustomerId(Long id, Pageable pageable);


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng xe của mỗi khách hàng
     */
    @Query(nativeQuery = true, value = "select json_arrayagg(json_object(" +
            "'full_name', project2_parking_management.statistics_total_car_customer.full_name," +
            "'total_car_customer', project2_parking_management.statistics_total_car_customer.total_car_customer))" +
            "from project2_parking_management.statistics_total_car_customer;")
    Object getTotalCarOfCustomer();

    // --------------------Vinh begin -----------------------
    List<Car> findAllByCustomerId(Long customerId);
    // --------------------Vinh end -------------------------
}
