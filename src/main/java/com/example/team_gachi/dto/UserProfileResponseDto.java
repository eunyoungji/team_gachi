package com.example.team_gachi.dto;

import com.example.team_gachi.common.ApiResponseDto;
import com.example.team_gachi.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponseDto extends ApiResponseDto {

    String username;
    String email;
    String nickname;

    public UserProfileResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
