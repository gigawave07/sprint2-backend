package com.sprint2.backend.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
