package com.sprint2.backend.services.app_account;

import java.util.List;

import com.sprint2.backend.entity.AppAccount;

public interface AppAccountService {
    List<AppAccount> findAll();

    AppAccount findByID(Long id);

    // ---------------------Vinh Begin-------------------------------

    AppAccount getAccount(String userName, String password);

    // ---------------------Vinh End---------------------------------
}
