package com.sprint2.backend.controller;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.app_account.AppAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AppAccountController {
    // ------------------------------Vinh Begin --------------------------------
    @Autowired
    private AppAccountService appAccountService;

    @GetMapping("check-login-mobile/{userName}/{password}")
    public ResponseEntity<?> checkLoginMobile(@PathVariable String userName, @PathVariable String password) {
        AppAccount appAccount = null;
        System.out.println(userName!=null);
        System.out.println(password!=null);
        if (userName!=null && password != null){
            appAccount = this.appAccountService.getAccount(userName, password);
        }
        return appAccount != null ? ResponseEntity.ok(appAccount) : ResponseEntity.ok(new MessageDTO("not found")) ;
    }
    // ------------------------------Vinh End ----------------------------------
}
