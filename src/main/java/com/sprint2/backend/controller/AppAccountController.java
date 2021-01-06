package com.sprint2.backend.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.sprint2.backend.config.JwtTokenProvider;
import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.model.LoginRequest;
import com.sprint2.backend.model.TokenDTO;
import com.sprint2.backend.model.UserDTO;
import com.sprint2.backend.model.UserDetailsImpl;
import com.sprint2.backend.repository.AppAccountRepository;
import com.sprint2.backend.services.app_role.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController("/app-account")
public class AppAccountController {
    public static String GOOGLE_CLIENT_ID = "103585693874-0bjkl21cmmjf8d9n09io95ciuveiievl.apps.googleusercontent.com";
    public static String PASSWORD = "123";
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
        String userName;
        userName = (String) payload.get("name");
        if (userRepository.existsByUsername(userName)) {
            appAccount = userRepository.findByUsername(userName);
        } else {
            appAccount = saveAccount(userName);
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
        appAccount.setEmail(value);
        appAccount.setPassword(passwordEncoder.encode(PASSWORD));
        AppRole appRole = roleService.findById(2L);
        appAccount.setAppRole(appRole);
        return userRepository.save(appAccount);
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam("to") String to){
        AppAccount appAccount = userRepository.findAppAccountByEmail(to);
        if(appAccount != null){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            emailInput = to;
            msg.setSubject("Mã xác nhận đặt lại mật khẩu.");
            int randomCode = ((int) Math.floor(Math.random() * 8999) + 10000);
            msg.setText("Mã xác nhận của bạn là: "+ randomCode);
            javaMailSender.send(msg);
            return new ResponseEntity<>(randomCode+"",HttpStatus.OK);
        }
        return new ResponseEntity<>( null,HttpStatus.OK);
    }
    @GetMapping("/resetPassWord")
    public ResponseEntity<Boolean> resetPassWord(@RequestParam("password") String password){
        AppAccount appAccount = userRepository.findAppAccountByEmail(emailInput);
        if(appAccount == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appAccount.setPassword(passwordEncoder.encode(password));
        userRepository.save(appAccount);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
