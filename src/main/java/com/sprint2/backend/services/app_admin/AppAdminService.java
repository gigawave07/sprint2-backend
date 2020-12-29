package com.sprint2.backend.services.app_admin;

import java.util.List;

import com.sprint2.backend.entity.AppAdmin;

public interface AppAdminService {
    List<AppAdmin> findAll();

    AppAdmin findByID(Long id);
}
