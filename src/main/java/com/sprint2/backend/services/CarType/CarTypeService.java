package com.sprint2.backend.services.CarType;

import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.model.CustomerDTO;

import java.util.List;

public interface CarTypeService {
    public List<CarType> findAll();
    public CarType findCarTypeByName(String string);
    public void saveType (CustomerDTO customerDTO);

}
