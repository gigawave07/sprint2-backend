package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.AppAccount;

@Repository
public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {
    // ---------------------Vinh Begin---------------------------------
    AppAccount findAllByUsername(String userName);
    // ---------------------Vinh End---------------------------------

    AppAccount findByUsername(String username);

    AppAccount findAppAccountByEmail(String email);

    Boolean existsByUsername(String username);
}
