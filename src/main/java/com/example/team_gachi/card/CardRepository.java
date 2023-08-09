package com.example.team_gachi.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    // Card findByUserId(Long id);
}
