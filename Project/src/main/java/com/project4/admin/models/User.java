package com.project4.admin.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 50,nullable = false, name = "firstname")
    private String firstname;
    @Column(length = 50,nullable = false, name = "lastname")
    private String lastname;
    @Column(length = 50,nullable = false, name = "password")
    private String password;
    @Column(length = 200,nullable = false, name = "email")
    private String email;
    @Column(length = 200,nullable = false, name = "phonenumber")
    private String phonenumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private Collection<FeedBack> feedbacks;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private Collection<Order> orders;
    public User() {
    }
    public User(String firstname,String lastname, String password,String email,String phonenumber) {
        this.firstname = firstname;
        this.lastname=lastname;
        this.password=password;
        this.email=email;
        this.phonenumber=phonenumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getFullName(){
        return this.getFirstname()+this.getLastname();
    }

}
