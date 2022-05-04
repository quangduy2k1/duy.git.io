package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Admin;
import com.project4.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public boolean check(String email, String password) {
        Optional<Admin> adminOptional = adminRepository.findByEmailAndPassword(email, password);
        return adminOptional.isPresent() && adminOptional.get().getPassword().equals(password) && adminOptional.get().getEmail().equals(email);
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public List<Admin> listAll() {
        return adminRepository.findAll();
    }

    public Admin get(Integer id) throws ModelException {
        return adminRepository.findById(id).orElseThrow(() -> new ModelException("could not find any Admin"));
    }

    public void delete(Integer id) {
        Admin result = adminRepository.findById(id).orElseThrow(() -> new ModelException("could not find any Admin"));
        adminRepository.delete(result);
    }
}
