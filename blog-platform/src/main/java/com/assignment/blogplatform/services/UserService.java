package com.assignment.blogplatform.services;

import com.assignment.blogplatform.entities.Role;
import com.assignment.blogplatform.entities.User;
import com.assignment.blogplatform.exceptions.CustomException;
import com.assignment.blogplatform.models.UserModel;
import com.assignment.blogplatform.repositories.RoleRepository;
import com.assignment.blogplatform.repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserModel createUser(UserModel user) {
        UserModel newUser;
        String userName = user.getUserName();
        User existingUser = userRepository.findByUserName(userName);
        if (existingUser == null) {
            newUser = saveUser(user);
            return newUser;
        } else {
            if (existingUser.isActive()) {
                throw new CustomException(env.getProperty("user.exists"));
            }
            user.setUserId(existingUser.getUserId());
            newUser = saveUser(user);
            return newUser;
        }
    }

    @Transactional
    public UserModel saveUser(UserModel user) {
        User newUser = new User();
        if (user.getUserId() != null) {
            newUser.setUserId(user.getUserId());
        }
        newUser.setUserName(user.getUserName());
        newUser.setPassword(env.getProperty("password.type") + user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setActive(true);
        newUser = userRepository.save(newUser);

        Role role = new Role();
        role.setUserName(user.getUserName());
        role.setRoleName(user.getRoleName());
        role = applyRole(role);

        user.setActive(newUser.isActive());
        user.setRoleName(role.getRoleName());
        user.setPassword(newUser.getPassword());
        user.setUserId(newUser.getUserId());
        user.setPassword(newUser.getPassword());
        return user;
    }

    @Transactional
    public User updatePassword(String userName, String newPassword) {
        if (userName != null && userRepository.existsByUserName(userName)) {
            User exixtingUser = userRepository.findByUserName(userName);
            exixtingUser.setPassword(env.getProperty("password.type") + newPassword);
            return userRepository.save(exixtingUser);
        } else {
            throw new CustomException(env.getProperty("user.not.found") + userName);
        }
    }

    @Transactional
    public UserModel updateUserRole(Role role) {
        String userName = role.getUserName();
        User existingUser = userRepository.findByUserName(userName);
        if (existingUser != null && existingUser.isActive()) {
            UserModel updatedUserRoles = new UserModel();
            updatedUserRoles.setRoleName(applyRole(role).getRoleName());
            updatedUserRoles.setUserId(existingUser.getUserId());
            updatedUserRoles.setUserName(existingUser.getUserName());
            updatedUserRoles.setPassword(existingUser.getPassword());
            updatedUserRoles.setEmail(existingUser.getEmail());
            updatedUserRoles.setActive(existingUser.isActive());
            return updatedUserRoles;
        } else {
            throw new CustomException(env.getProperty("user.not.found"));
        }
    }

    @Transactional
    public Role applyRole(Role role) {
        String roleName = role.getRoleName();
        String userName = role.getUserName();
        if (!roleRepository.existsByRoleNameAndUserName(roleName, userName)) {
            role.setRoleName(env.getProperty("role.prefix") + roleName);
            return roleRepository.save(role);
        } else {
            throw new CustomException(env.getProperty("user.not.found"));
        }
    }

    @Transactional
    public UserModel removeUser(Role role) {
        UserModel userModel = new UserModel();
        String roleName = env.getProperty("remove.user");
        String userName = role.getUserName();
        User exixtingUser = userRepository.findByUserName(userName);
        if (exixtingUser != null) {
            roleRepository.deleteByUserName(userName);

            userModel.setUserId(exixtingUser.getUserId());
            userModel.setUserName(userName);
            userModel.setPassword(exixtingUser.getPassword());
            userModel.setEmail(exixtingUser.getEmail());
            userModel.setActive(false);
            userModel.setRoleName(roleName);

            exixtingUser.setActive(false);
            userRepository.save(exixtingUser);
            return userModel;
        } else {
            throw new CustomException(env.getProperty("user.not.found"));
        }
    }
}
