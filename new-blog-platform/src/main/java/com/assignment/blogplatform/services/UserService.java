package com.assignment.blogplatform.services;

import com.assignment.blogplatform.entities.Role;
import com.assignment.blogplatform.entities.User;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.repositories.RoleRepository;
import com.assignment.blogplatform.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        User existsUser = userRepository.findByUserName(user.getUserName());
        Role existsRole = roleRepository.findByRoleName(env.getProperty("role.prefix") + user.getRole().getRoleName());
        if (existsUser != null && !existsUser.isActive()) {
            if (existsRole != null) {
            existsUser.setRole(existsRole);
            } else {
                throw new CustomException(env.getProperty("no.role"));
            }
            existsUser.setActive(true);
            return userRepository.save(existsUser);

        } else if (existsUser == null) {
            if (existsRole != null) {
                user.setActive(true);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole(existsRole);
                return userRepository.save(user);
            } else {
                throw new CustomException(env.getProperty("no.role"));
            }

        } else {
            throw new CustomException(env.getProperty("user.exists"));
        }
    }

    public User updatePassword(String userName, String newPassword) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        } else {
            throw new CustomException(env.getProperty("no.user"));
        }
    }

    public User updateUserRole(String userName, String roleName) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            Role existsRole = roleRepository.findByRoleName(env.getProperty("role.prefix") + roleName);
            if (existsRole != null) {
                user.setRole(existsRole);
                return userRepository.save(user);
            } else {
                throw new CustomException(env.getProperty("no.role"));
            }
        } else {
            throw new CustomException(env.getProperty("no.user"));
        }
    }

    public User removeUser(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            user.setActive(false);
            return userRepository.save(user);
        } else {
            throw new CustomException(env.getProperty("no.user"));
        }
    }
}