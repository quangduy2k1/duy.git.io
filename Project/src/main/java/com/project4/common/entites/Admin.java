package com.project4.common.entites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Data
@RequiredArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @Column(length = 50, nullable = false, name = "firstname")
    private String firstname;
    @Column(length = 50, nullable = false, name = "lastname")
    private String lastname;
    @Column(length = 50, nullable = false, name = "password")
    private String password;
    @Column(length = 200, nullable = false, name = "email")
    private String email;
}
