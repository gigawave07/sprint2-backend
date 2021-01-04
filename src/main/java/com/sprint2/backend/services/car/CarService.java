package com.sprint2.backend.services.car;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.sprint2.backend.entity.Car;

public interface CarService {
    List<Car> findAll();

    Car findByID(Long id);

    // Thống kê số lượng xe của mỗi khách hàng
    Object getTotalCarOfCustomer();

    //  Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
    Object getToTalCustomerRegisterPeriod(String fromDay, String toDay) throws ParseException;
}
