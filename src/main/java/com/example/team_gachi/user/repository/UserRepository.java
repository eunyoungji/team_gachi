package com.example.team_gachi.user.repository;

import com.example.team_gachi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String Id);
    Optional<User> findByNickname(String nickname);
}
