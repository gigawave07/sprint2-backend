package com.sprint2.backend.services.car;

import com.sprint2.backend.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findByID(Long id);


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng xe của mỗi khách hàng
     */
    Object getTotalCarOfCustomer();

}
