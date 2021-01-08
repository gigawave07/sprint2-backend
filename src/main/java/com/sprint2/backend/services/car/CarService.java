package com.sprint2.backend.services.car;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sprint2.backend.model.CarDTO;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    /**
     * nguyen quoc khanh
     * @param id
     * @param pageable
     * @return
     */
    Page<Car>findAllCarByCustomerId(Long id, int pageable);

    Car findByID(Long id);

    // Quan start
    Car findByPlateNumber(String plateNumber);

    void save(Car car);

    Car convert(CarDTO car);

    // Quan end


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng xe của mỗi khách hàng
     */
    Object getTotalCarOfCustomer();

}
