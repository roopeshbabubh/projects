package com.assignment.blogplatform.repositories;

import com.assignment.blogplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    User findByUserId(long userId);
}
