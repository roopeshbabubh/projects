package com.assignment.blogplatform.controllers;

import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.models.UserModel;
import com.assignment.blogplatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class RegistrationController {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody UserModel user) {
        if (user.getUserName() != null && !user.getUserName().isEmpty()) {
            var newUser = userService.createUser(user);
            user.setUserId(newUser.getUserId());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalided.data"));
        }
    }
}
