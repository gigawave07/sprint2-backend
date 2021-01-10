package com.sprint2.backend.services.app_account;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.sprint2.backend.entity.AppAccount;

import javax.mail.MessagingException;

public interface AppAccountService {
    List<AppAccount> findAll();

    AppAccount findByID(Long id);

    //    khanh start
    void save(AppAccount appAccount);
    AppAccount setVerifyCode(Long id, String siteURL) throws UnsupportedEncodingException,
            MessagingException;


    // ---------------------Vinh Begin-------------------------------

    AppAccount getAccount(String userName, String password);

    // ---------------------Vinh End---------------------------------
}
