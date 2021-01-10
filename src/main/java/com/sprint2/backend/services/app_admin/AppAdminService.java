package com.sprint2.backend.services.app_admin;

import com.sprint2.backend.entity.AppAdmin;

import java.util.List;

public interface AppAdminService {
    List<AppAdmin> findAll();

    AppAdmin findByID(Long id);
}
