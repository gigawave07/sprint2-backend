package com.sprint2.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String employeeCode;
    @Size(min = 10, max = 45,  message = "Vui lòng nhập đúng định dạng")
    private String fullName;
    private Boolean gender;
    @Pattern(regexp = "^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$", message = "Email không hợp lệ")
    private String email;
    private String address;
    private LocalDate birthday;
    private String phoneNumber;
    private String position;
    private String password;
    private String role;
}
