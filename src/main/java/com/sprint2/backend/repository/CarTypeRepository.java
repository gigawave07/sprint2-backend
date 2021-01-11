package com.sprint2.backend.repository;

import com.sprint2.backend.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    CarType findByCarTypeNameContains(String name);
}
