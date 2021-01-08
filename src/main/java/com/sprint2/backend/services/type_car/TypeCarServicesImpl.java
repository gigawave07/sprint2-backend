package com.sprint2.backend.services.type_car;

import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCarServicesImpl implements TypeCarServices {
    @Autowired
    CarTypeRepository carTypeRepository;

    @Override
    public List<CarType> findAll() {
        return this.carTypeRepository.findAll();
    }
}
