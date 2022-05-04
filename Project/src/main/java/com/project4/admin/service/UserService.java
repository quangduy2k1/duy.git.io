package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.User;
import com.project4.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User get(Integer id) throws ModelException {
        return userRepository.findById(id).orElseThrow(() -> new ModelException("could not find any User with Id" + id));
    }

    public void delete(Integer id) {
        User result = userRepository.findById(id).orElseThrow(() -> new ModelException("could not find any User with Id" + id));
        userRepository.delete(result);
    }

    public boolean check(String email, String password) {
        Optional<User> adminOptional = userRepository.findByEmailAndPassword(email, password);
        return adminOptional.isPresent() && adminOptional.get().getPassword().equals(password) && adminOptional.get().getEmail().equals(email);
    }
}
