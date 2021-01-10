package com.sprint2.backend.services.CarTypeNgan;

import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.model.NganCustomerDTO;
import com.sprint2.backend.repository.CarRepository;
import com.sprint2.backend.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Ngan's tasks
import java.util.List;
@Service
public class CarTypeServiceImpl implements CarTypeService {
    @Autowired
    CarTypeRepository carTypeRepository;
    @Autowired
    CarRepository carRepository;
    @Override
    public List<CarType> findAll() {
        return carTypeRepository.findAll();
    }

    @Override
    public CarType findCarTypeByName(String string) {
        return carTypeRepository.findCarTypeByCarTypeName(string);
    }

    @Override
    public void saveType(NganCustomerDTO nganCustomerDTO) {
//        Car car = carRepository.findCarByPlateNumber(customerDTO.getPlateNumber());
//       CarType cartype = new CarType();
//        cartype.setCarTypeName(customerDTO.getCarTypeName());
//        this.carTypeRepository.save(cartype);
    }
}
//End Ngan's tasks
