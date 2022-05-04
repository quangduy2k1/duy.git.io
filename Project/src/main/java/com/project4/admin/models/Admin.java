package com.project4.admin.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    @Column(length = 50,nullable = false, name = "firstname")
    private String firstname;
    @Column(length = 50,nullable = false, name = "lastname")
    private String lastname;
    @Column(length = 50,nullable = false, name = "password")
    private String password;
    @Column(length = 200,nullable = false, name = "email")
    private String email;

    public Admin() {
    }
    public Admin(String firstname, String lastname, String password, String email) {
        this.firstname=firstname;
        this.lastname=lastname;
        this.password=password;
        this.email=email;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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


}
