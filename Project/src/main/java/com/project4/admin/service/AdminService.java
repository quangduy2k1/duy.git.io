package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Admin;
import com.project4.admin.models.Category;
import com.project4.admin.repository.AdminRepository;
import com.project4.admin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repo;
    public boolean chek(String email,String password){
        Optional<Admin> adminOptional=repo.findByEmailAndPassword(email, password);
        if(adminOptional.isPresent()&&adminOptional.get().getPassword().equals(password) && adminOptional.get().getEmail().equals(email)){
            return true;
        }
        return false;
    }
    public void save(Admin admin) {
        repo.save(admin);
    }
    public List<Admin> listAll(){
        return repo.findAll();
    }
    public Admin get(Integer id) throws ModelException {
        Optional<Admin> result=repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ModelException("could not find any Admin with Id" +id);
    }
    public void delete(Integer id){
        Optional<Admin> result=repo.findById(id);
        if (result.isPresent()){
            repo.delete(result.get());
        }
        throw new ModelException("could not find any Admin");
    }
}
