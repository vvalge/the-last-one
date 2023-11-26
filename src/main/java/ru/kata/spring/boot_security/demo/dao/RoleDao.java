package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Roles;

import java.util.List;

public interface RoleDao {
    boolean addRole(Roles role);

    Roles findByName(String name);

    List<Roles> listByName(List<String> name);
}

