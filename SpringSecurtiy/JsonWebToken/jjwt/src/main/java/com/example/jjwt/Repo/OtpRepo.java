package com.example.jjwt.Repo;

import com.example.jjwt.Entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepo extends JpaRepository<Otp,String> {
    Optional<Otp> findUserByUsername(String username);
}
