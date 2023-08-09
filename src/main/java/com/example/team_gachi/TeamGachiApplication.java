package com.example.team_gachi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeamGachiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamGachiApplication.class, args);
    }

}
