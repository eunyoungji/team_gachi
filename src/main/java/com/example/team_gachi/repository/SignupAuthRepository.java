package com.example.team_gachi.repository;

import com.example.team_gachi.user.entity.SignupAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupAuthRepository extends JpaRepository<SignupAuth, Long> {

    Optional<SignupAuth> findByEmail(String email);
}
