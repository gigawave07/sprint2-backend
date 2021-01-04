package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    Long id;
    String fullName;
    Boolean gender;
    String email;
    String birthday;
    String phone;
    String address;
    String identityNumber;
    String imageAvatar;
}
