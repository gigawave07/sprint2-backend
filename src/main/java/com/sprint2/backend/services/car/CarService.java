package com.sprint2.backend.services.car;

import java.util.List;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
