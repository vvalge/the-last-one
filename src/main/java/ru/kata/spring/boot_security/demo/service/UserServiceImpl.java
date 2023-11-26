package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Roles;
import ru.kata.spring.boot_security.demo.model.Users;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Transactional(readOnly = true)
    public Users findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public boolean saveUser(Users user) {
        Users userBase = userDao.findByEmail(user.getUsername());
        if (userBase != null) {
            return false;
        }
        user.setPassword(user.getPassword());
        System.out.println(user);
        List<String> list = user.getRoles().stream().map(Roles::getRole).collect(Collectors.toList());
        List<Roles> roleList = listByRole(list);
        user.setRoles(roleList);
        userDao.create(user);
        return true;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(Users user) {
        Users userBase = findById(user.getId());
        if (!userBase.getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
        List<String> list = user.getRoles().stream().map(Roles::getRole).collect(Collectors.toList());
        List<Roles> roleList = listByRole(list);
        user.setRoles(roleList);
        userDao.updateUser(user);
    }

    public List<Roles> listByRole(List<String> name) {
        return roleDao.listByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userDao.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
