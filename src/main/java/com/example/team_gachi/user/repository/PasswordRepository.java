package com.example.team_gachi.user.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordRepository extends JpaRepository<Password, Long> {

    Optional<Password> findAllByUser(User user);


}
