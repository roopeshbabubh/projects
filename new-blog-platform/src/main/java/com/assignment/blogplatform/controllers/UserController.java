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
        if (!userName.isEmpty() && !newPassword.isEmpty()) {
            var updatedUser = userService.updatePassword(userName, newPassword);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalid.data"));
        }
    }

    @PostMapping("/change-roles")
    public ResponseEntity<?> changeUserRole(@RequestBody UserModel userModel) {
        String userName = userModel.getUserName();
        String roleName = userModel.getRoleName();
        if (!userName.isEmpty() && !roleName.isEmpty()) {
            var updatedUser = userService.updateUserRole(userName, roleName);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalid.data"));
        }
    }

    @DeleteMapping("/remove-user")
    public ResponseEntity<?> deleteUser(@RequestBody UserModel userModel) {
        String userName = userModel.getUserName();
        if (!userName.isEmpty()) {
            var updatedBlog = userService.removeUser(userName);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } else {
            throw new CustomException(env.getProperty("invalid.data"));
        }
    }
}


