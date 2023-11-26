package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Users;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users findById(Long id);

    List<Users> getAllUsers();

    boolean saveUser(Users users);

    void updateUser(Users user);

    void deleteById(Long id);
}

