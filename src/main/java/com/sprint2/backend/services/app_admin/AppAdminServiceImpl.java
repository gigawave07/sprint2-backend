package com.sprint2.backend.services.app_admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.AppAdmin;
import com.sprint2.backend.repository.AppAdminRepository;

@Service
public class AppAdminServiceImpl implements AppAdminService {
    @Autowired
    private AppAdminRepository appAdminRepository;

    @Override
    public List<AppAdmin> findAll() {
        return this.appAdminRepository.findAll();
    }

    @Override
    public AppAdmin findByID(Long id) {
        return this.appAdminRepository.findById(id).orElse(null);
    }
}
