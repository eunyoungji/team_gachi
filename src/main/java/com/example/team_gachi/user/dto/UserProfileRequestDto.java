package com.example.team_gachi.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequestDto {
    String username;
    String password;
    String nickname;
}
