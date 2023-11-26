package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Users;

import java.util.List;

public interface UserDao {
    Users findById(Long id);

    List<Users> getAllUsers();

    boolean create(Users user);

    void deleteById(Long id);

    void updateUser(Users user);

    Users findByEmail(String email);
}
