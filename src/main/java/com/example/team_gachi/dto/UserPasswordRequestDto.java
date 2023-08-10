package com.example.team_gachi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordRequestDto {
    private String password;
    private String new_password;
}
