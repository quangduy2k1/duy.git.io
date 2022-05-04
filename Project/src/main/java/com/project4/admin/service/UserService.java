package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Admin;
import com.project4.admin.models.Product;
import com.project4.admin.models.User;
import com.project4.admin.repository.AdminRepository;
import com.project4.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    public void save(User user) {
        repo.save(user);
    }
    public List<User> listAll(){
        return repo.findAll();
    }
    public User get(Integer id) throws ModelException {
        Optional<User> result=repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ModelException("could not find any User with Id" +id);
    }
    public void delete(Integer id){
        Optional<User> result=repo.findById(id);
        if (result.isPresent()){
            repo.delete(result.get());
        }
        throw new ModelException("could not find any User");
    }
    public boolean chek(String email,String password){
        Optional<User> adminOptional=repo.findByEmailAndPassword(email, password);
        if(adminOptional.isPresent()&&adminOptional.get().getPassword().equals(password) && adminOptional.get().getEmail().equals(email)){
            return true;
        }
        return false;
    }
}
