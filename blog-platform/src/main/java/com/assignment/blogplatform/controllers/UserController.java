package com.assignment.blogplatform.controllers;

import com.assignment.blogplatform.entities.Role;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.models.UserModel;
import com.assignment.blogplatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logged-users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody UserModel userModel) {
        String userName = userModel.getUserName();
        String newPassword = userModel.getPassword();
        if (userName != null && newPassword != null) {
            var updatedUser = userService.updatePassword(userName, newPassword);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalided.data"));
        }
    }

    @PostMapping("/add-roles")
    public ResponseEntity<?> addRolesToUser(@RequestBody Role role) {
        String userName = role.getUserName();
        if (userName != null && role.getRoleName() != null) {
            var updatedUser = userService.updateUserRole(role);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalided.data"));
        }
    }

    @DeleteMapping("/remove-user")
    public ResponseEntity<?> deleteUser(@RequestBody Role role) {
        String userName = role.getUserName();
        if (userName != null) {
            var updatedBlog = userService.removeUser(role);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalided.data"));
        }
    }
}


