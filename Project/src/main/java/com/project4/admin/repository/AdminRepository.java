package com.project4.admin.repository;

import com.project4.admin.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Optional<Admin> findByEmailAndPassword(String email, String password);
}
