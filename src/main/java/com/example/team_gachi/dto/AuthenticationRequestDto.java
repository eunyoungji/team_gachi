package com.example.team_gachi.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class AuthenticationRequestDto {
    @Email
    private String email;
}
