package com.sprint2.backend.services.app_role;

import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.repository.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppRoleServiceImpl implements AppRoleService {
    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public List<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppRole findById(Long idRole) {
        return appRoleRepository.findById(idRole).orElse(null);
    }
}
