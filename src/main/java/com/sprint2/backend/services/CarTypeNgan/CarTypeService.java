package com.sprint2.backend.services.CarTypeNgan;

import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.model.NganCustomerDTO;

import java.util.List;

public interface CarTypeService {
    //Ngan's tasks
    public List<CarType> findAll();
    public CarType findCarTypeByName(String string);
    public void saveType (NganCustomerDTO nganCustomerDTO);
    //End Ngan's tasks
}
