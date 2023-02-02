package com.aegis.TechMarket.Services;

import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Exceptions.AppException;
import com.aegis.TechMarket.Repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User create(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new AppException("Not found user with id: " + id, HttpStatus.NOT_FOUND);
        });
    }

    public User update(User user) {
        var updatedUser = findById(user.getId());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFullName(user.getFullName());
        if(!Objects.equals(user.getPassword(), ""))
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        updatedUser.setAddress(user.getAddress());

        return userRepository.save(updatedUser);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean exists(String email){
        return userRepository.existsByEmail(email);
    }


}
