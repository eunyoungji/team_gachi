package com.example.team_gachi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VerificationRequestDto {


    private String email;


    private String authKey;
}
