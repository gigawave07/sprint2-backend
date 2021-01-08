package com.sprint2.backend.services.app_account;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.repository.AppAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
