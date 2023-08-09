package com.example.team_gachi.user.dto;

import com.example.team_gachi.common.ApiResponseDto;
import com.example.team_gachi.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponseDto extends ApiResponseDto {
    String userid;
    String username;
    String nickname;

    public UserProfileResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}
