package com.sprint2.backend.controller;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.services.email.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
public class EmailController {
    /**
     * nguyen quoc khanh
     */
    @Autowired
    EmailServices emailServices;

}
