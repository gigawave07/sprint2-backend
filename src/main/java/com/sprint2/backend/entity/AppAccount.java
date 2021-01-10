package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "app_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;

    // 2 properties for email verification
    @Column(name = "enabled", columnDefinition = "BIT")
    private Boolean enabled = false;

    @Column(name = "verification_code", columnDefinition = "VARCHAR(64)")
    private String verificationCode;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "app_role_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppRole appRole;

    @OneToOne(mappedBy = "appAccount", cascade = CascadeType.ALL)
    @JsonBackReference
    private AppAdmin appAdmin;

    @OneToOne(mappedBy = "appAccount", cascade = CascadeType.ALL)
    @JsonBackReference
    private Employee employee;

    @OneToOne(mappedBy = "appAccount", cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;
}

