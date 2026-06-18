package com.task.repository;

import com.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String id);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
