package com.assignment.blogplatform.repositories;

import com.assignment.blogplatform.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByUserNameAndRoleName(String userName, String roleName);
    Role findByUserName(String userName);

    void deleteByUserName(String userName);

    boolean existsByRoleNameAndUserName(String roleName, String userName);
}
