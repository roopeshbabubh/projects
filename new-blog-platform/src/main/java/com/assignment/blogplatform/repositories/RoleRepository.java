package com.assignment.blogplatform.repositories;

import com.assignment.blogplatform.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);
}
