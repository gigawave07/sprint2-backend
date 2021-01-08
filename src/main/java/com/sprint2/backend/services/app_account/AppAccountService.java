package com.sprint2.backend.services.app_account;

import com.sprint2.backend.entity.AppAccount;

import java.util.List;

public interface AppAccountService {
    List<AppAccount> findAll();

    AppAccount findByID(Long id);
}
