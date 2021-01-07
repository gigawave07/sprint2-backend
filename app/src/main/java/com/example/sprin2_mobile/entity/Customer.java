package com.example.sprin2_mobile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    String id;
    String customerCode;
    String fullName;
    String gender;
    String email;
    String birthday;
    String phone;
    String address;
    String identityNumber;
    String imageAvatar;
    String createDate;
    AppAccount appAccount;
}
