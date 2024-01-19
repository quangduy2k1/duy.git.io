package com.project4.admin.user;

import com.project4.common.exception.ModelException;
import com.project4.common.entites.User;
import com.project4.common.repository.UserRepository;
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

}
