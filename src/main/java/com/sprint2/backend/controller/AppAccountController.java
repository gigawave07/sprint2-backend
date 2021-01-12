package com.sprint2.backend.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.sprint2.backend.config.JwtTokenProvider;
import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.*;
import com.sprint2.backend.repository.AppAccountRepository;
import com.sprint2.backend.repository.CustomerRepository;
import com.sprint2.backend.services.app_account.AppAccountService;
import com.sprint2.backend.services.app_role.AppRoleService;
import com.sprint2.backend.services.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    // p Khanh start
    public static String GOOGLE_CLIENT_ID = "103585693874-0bjkl21cmmjf8d9n09io95ciuveiievl.apps.googleusercontent.com";
    public static String PASSWORD = "12345678";
    private static String emailInput;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppRoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AppAccountRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserDTO(jwt,
                userDetails.getIdUser(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("login-google")
    public ResponseEntity<?> authenticateByGoogleAccount(@RequestBody TokenDTO tokenDTO) throws IOException {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDTO.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        AppAccount appAccount;
        String email;
        email = (String) payload.get("email");
        if (userRepository.existsByUsername(email)) {
            appAccount = userRepository.findByUsername(email);
        } else {
            appAccount = saveAccount(email);
        }
        UserDTO userDTO = login(appAccount);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("login-facebook")
    public ResponseEntity<?> authenticateByFacebookAccount(@RequestBody TokenDTO tokenDTO) throws IOException {
        Facebook facebook = new FacebookTemplate(tokenDTO.getValue());
        final String[] fields = {"name", "picture"};
        org.springframework.social.facebook.api.User userFacebook = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        AppAccount appAccount;
        System.out.println(userFacebook);
        if (userFacebook.getName() == null) {
            appAccount = saveAccount(userFacebook.getName());
        } else {
            if (userRepository.existsByUsername(userFacebook.getName())) {
                appAccount = userRepository.findByUsername(userFacebook.getName());
            } else {
                appAccount = saveAccount(userFacebook.getName());
            }
        }
        UserDTO userDTO = login(appAccount);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    private UserDTO login(AppAccount user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), PASSWORD)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new UserDTO(jwt,
                userDetails.getIdUser(),
                userDetails.getUsername(),
                roles);
    }

    private AppAccount saveAccount(String value) {
        AppAccount appAccount = new AppAccount();
        appAccount.setUsername(value);
        appAccount.setPassword(passwordEncoder.encode(PASSWORD));
        AppRole appRole = roleService.findById(3L);
        appAccount.setAppRole(appRole);

        Customer customer = new Customer();


        return userRepository.save(appAccount);

    }

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam("to") String to) {
        AppAccount appAccount = userRepository.findByUsername(to);
        if (appAccount != null) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            emailInput = to;
            msg.setSubject("Mã xác nhận đặt lại mật khẩu.");
            int randomCode = ((int) Math.floor(Math.random() * 8999) + 10000);
            msg.setText("Mã xác nhận của bạn là: " + randomCode);
            javaMailSender.send(msg);
            return new ResponseEntity<>(randomCode + "", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/resetPassWord")
    public ResponseEntity<Boolean> resetPassWord(@RequestParam("password") String password) {
        AppAccount appAccount = userRepository.findByUsername(emailInput);
        if (appAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appAccount.setPassword(passwordEncoder.encode(password));
        userRepository.save(appAccount);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/find-by/{username}")
    public ResponseEntity<AppAccount> findByName(@PathVariable("username") String username) {
        AppAccount appAccount = userRepository.findByUsername(username);
        if (appAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appAccount, HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<Integer> saveUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (userRepository.existsByUsername(username)) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else if (!customerRepository.existsByEmail(username)) {
            return new ResponseEntity<>(2, HttpStatus.OK);
        }
        AppAccount appAccount = new AppAccount();
        appAccount.setPassword(passwordEncoder.encode(password));
        appAccount.setAppRole(roleService.findById(3L));
        appAccount.setUsername(username);
        userRepository.save(appAccount);
        Customer customer =  customerRepository.findByEmail(username);
        customer.setAppAccount(appAccount);
        customerRepository.save(customer);


        return new ResponseEntity<>(3, HttpStatus.OK);
    }
    // p Khanh end
}
