package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.CarType;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    CarType findCarTypeByCarTypeName (String string);
}
