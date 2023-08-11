package com.example.team_gachi.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SignupAuth {
    @Id
    String email;

    @Column(nullable = false)
    private String authkey;

    @Column(nullable = false)
    private int authStatus;



    public SignupAuth(String email, String authkey, int authStatus) {
        this.email = email;
        this.authkey = authkey;
        this.authStatus = authStatus;

    }
}
