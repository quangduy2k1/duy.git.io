package com.project4.common.entites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 50, nullable = false, name = "firstname")
    private String firstname;
    @Column(length = 50, nullable = false, name = "lastname")
    private String lastname;
    @Column(length = 50, nullable = false, name = "password")
    private String password;
    @Column(length = 200, nullable = false, name = "email")
    private String email;
    @Column(length = 200, nullable = false, name = "phonenumber")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Collection<FeedBack> feedbacks;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Collection<Order> orders;
}
