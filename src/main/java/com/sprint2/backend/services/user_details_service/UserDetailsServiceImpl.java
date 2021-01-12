package com.sprint2.backend.services.user_details_service;


import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.model.UserDetailsImpl;
import com.sprint2.backend.repository.AppAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AppAccountRepository appAccountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppAccount appAccount = appAccountRepository.findByUsername(username);
        if (appAccount == null) {
            throw new UsernameNotFoundException("Not Found!!!");
        }
        return UserDetailsImpl.build(appAccount);
    }
}
