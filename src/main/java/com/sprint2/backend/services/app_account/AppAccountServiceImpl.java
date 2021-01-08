package com.sprint2.backend.services.app_account;

import com.sprint2.backend.services.email.EmailServices;
import com.sprint2.backend.services.email.EmailServicesImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.repository.AppAccountRepository;

import javax.mail.MessagingException;

@Service
public class
AppAccountServiceImpl implements AppAccountService {
    @Autowired
    private AppAccountRepository appAccountRepository;
    @Autowired
    EmailServices emailServices;

    @Override
    public List<AppAccount> findAll() {
        return this.appAccountRepository.findAll();
    }

    @Override
    public AppAccount findByID(Long id) {
        return this.appAccountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AppAccount appAccount) {
        this.appAccountRepository.save(appAccount);
    }

    @Override
    public AppAccount setVerifyCode(Long id, String siteURL)
            throws UnsupportedEncodingException, MessagingException {
        AppAccount appAccount = this.appAccountRepository.findById(id).orElse(null);
        String randomCode = RandomString.make(8);
        assert appAccount != null;
        appAccount.setVerificationCode(randomCode);
        appAccountRepository.save(appAccount);
        emailServices.sendVerificationEmail(appAccount, siteURL);
        return appAccount;
    }
}

