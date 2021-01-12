package com.sprint2.backend.services.app_role;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;

import java.util.List;

public interface AppRoleService {
    List<com.sprint2.backend.entity.AppRole> findAll();
    AppRole findById(Long idRole);
}
