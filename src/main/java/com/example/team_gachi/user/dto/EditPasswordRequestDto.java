package com.example.team_gachi.user.dto;

import lombok.Getter;

@Getter
public class EditPasswordRequestDto {
    private String password;
    private String new_password;
}
