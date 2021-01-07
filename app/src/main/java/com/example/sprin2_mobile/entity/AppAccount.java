package com.example.sprin2_mobile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppAccount {
    Long id;
    Boolean enabled;
    String username;
    String verificationCode;
    AppRole appRole;


}
