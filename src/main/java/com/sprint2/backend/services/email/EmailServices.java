package com.sprint2.backend.services.email;

import com.sprint2.backend.entity.AppAccount;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailServices {
    void sendVerificationEmail(AppAccount appAccount, String siteURL) throws UnsupportedEncodingException, MessagingException;
}
