package com.sprint2.backend.repository;

import com.sprint2.backend.entity.AppAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppAdminRepository extends JpaRepository<AppAdmin, Long> {
}
