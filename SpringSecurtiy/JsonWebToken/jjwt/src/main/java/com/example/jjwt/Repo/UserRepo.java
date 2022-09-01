package com.example.jjwt.Repo;

import com.example.jjwt.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
