package com.project4.admin.repository;

import com.project4.admin.models.Admin;
import com.project4.admin.models.Product;
import com.project4.admin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmailAndPassword(String email, String password);

}
