package com.sprint2.backend.services.app_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.repository.AppAccountRepository;

@Service
public class AppAccountServiceImpl implements AppAccountService {
    @Autowired
    private AppAccountRepository appAccountRepository;

    @Override
    public List<AppAccount> findAll() {
        return this.appAccountRepository.findAll();
    }

    @Override
    public AppAccount findByID(Long id) {
        return this.appAccountRepository.findById(id).orElse(null);
    }

    // ---------------------Vinh Begin-------------------------------

    @Override
    public AppAccount getAccount(String userName, String password) {
        return this.appAccountRepository.findAllByAndUsernameAndPassword(userName, password);
    }
    // ---------------------Vinh End---------------------------------
}
