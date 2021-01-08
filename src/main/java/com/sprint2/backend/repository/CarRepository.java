package com.sprint2.backend.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Car;

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
}
