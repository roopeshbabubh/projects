package com.assignment.blogplatform.controllers;

import com.assignment.blogplatform.entities.User;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class RegistrationController {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody User user) {
        if (!user.getPassword().isEmpty() && !user.getUserName().isEmpty()) {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalid.data"));
        }
    }
}