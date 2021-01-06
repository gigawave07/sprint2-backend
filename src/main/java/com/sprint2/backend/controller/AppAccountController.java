package com.sprint2.backend.controller;

import com.sprint2.backend.entity.AppAccount;
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

    @GetMapping("checkLoginMobile/{userName}/{password}")
    public ResponseEntity<AppAccount> checkLoginMobile(@PathVariable String userName, @PathVariable String password) {
        return ResponseEntity.ok(this.appAccountService.getAccount(userName, password));
    }
    // ------------------------------Vinh End ----------------------------------
}
