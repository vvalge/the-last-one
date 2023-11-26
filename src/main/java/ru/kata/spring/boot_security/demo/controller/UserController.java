package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.model.Users;


@Controller
public class UserController {

    @GetMapping("/user")
    public String returnUserPage(){
        return "indexUser";
    }


    @ResponseBody
    @GetMapping("/api/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Users> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = (Users) authentication.getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}