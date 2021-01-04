package com.sprint2.backend.services.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car findByID(Long id) {
        return this.carRepository.findById(id).orElse(null);
    }

    // Thống kê số lượng xe của mỗi khách hàng
    @Override
    public Object getTotalCarOfCustomer() {
        return this.carRepository.getTotalCarOfCustomer();
    }

    //  Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
    @Override
    public Object getToTalCustomerRegisterPeriod(String fromDay, String toDay) throws ParseException {
        System.out.println(fromDay);
        System.out.println(toDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localFromDay = LocalDate.parse(fromDay, formatter);
        LocalDate localToDay = LocalDate.parse(toDay, formatter);
        return this.carRepository.getToTalCustomerRegisterPeriod(localFromDay, localToDay);
    }


}
