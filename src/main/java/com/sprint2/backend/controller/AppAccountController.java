package com.sprint2.backend.controller;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.model.ChangePasswordUserDTO;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.app_account.AppAccountService;
import com.sprint2.backend.services.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AppAccountController {
    @Autowired
    AppAccountService appAccountService;

    /**
     * nguyen quoc khanh
     * @param id
     * @return
     * get account by id
     */
    @GetMapping("/findAppAccountById/{id}")
    public ResponseEntity<AppAccount> getAppAccountById(@PathVariable Long id) {
        AppAccount appAccount = this.appAccountService.findByID(id);
        return new ResponseEntity<AppAccount>(appAccount, HttpStatus.OK);
    }

    /**
     * @param id
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     * set verifycode when user change their password
     */
    @PostMapping("/setVerifyCode/{id}")
    public ResponseEntity<AppAccount> confirmEmail(@PathVariable Long id, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        String siteURL = request.getRequestURL().toString();
        return ResponseEntity.ok(appAccountService.setVerifyCode(id, siteURL));
    }

    /**
     * @param id
     * @param changePasswordUserDTO
     * @return
     * confirm veryficode when user change their password
     */
    @PutMapping("/veryficode/{id}")
    public boolean verifyAccount(@PathVariable Long id,
                                 @RequestBody ChangePasswordUserDTO changePasswordUserDTO) {
        AppAccount appAccount = this.appAccountService.findByID(id);
        boolean verify = changePasswordUserDTO.getVerificationCode().equals(appAccount.getVerificationCode());
        if (verify) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param id
     * @param changePasswordUserDTO
     * @return
     * save new password
     */
    @PutMapping("/savePassword/{id}")
    public ResponseEntity<AppAccount> savePassword(@PathVariable Long id, @RequestBody ChangePasswordUserDTO changePasswordUserDTO) {
        AppAccount appAccount = this.appAccountService.findByID(id);
        appAccount.setPassword(changePasswordUserDTO.getPasswordNew());
        this.appAccountService.save(appAccount);
        return new ResponseEntity<AppAccount>(appAccount, HttpStatus.OK);
    }

    /**
     * @param changePasswordUserDTO
     * @param id
     * @return
     * confirm old password
     */
    @PutMapping("/confirmPassword/{id}")
    public ResponseEntity<?> getAccountByUsername(@RequestBody ChangePasswordUserDTO changePasswordUserDTO,
                                                  @PathVariable Long id) {
        AppAccount appAccount = this.appAccountService.findByID(id);
        boolean isMatch = changePasswordUserDTO.getPasswordOld().equals(appAccount.getPassword());
        if (isMatch) {
            return ResponseEntity.ok(new MessageDTO("Wright password"));
        } else {
            return ResponseEntity.ok(new MessageDTO("Wrong password"));
        }
//        boolean isMatch = bcryptEncoder.matches(changePasswordAdminDTO.getPasswordOld(), appAccount.getPassword());
//        if (isMatch) {
//            appAccount.setPassword(bcryptEncoder.encode(changePasswordAdminDTO.getPasswordNew()));
//            return ResponseEntity.ok(new MessageDTO("Wright password));
//        } else {
//            return ResponseEntity.ok(new MessageDTO("Wrong password"));
//        }
    }

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
