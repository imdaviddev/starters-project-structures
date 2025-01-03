package com.backend.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.app.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByUsername(String username);
}
