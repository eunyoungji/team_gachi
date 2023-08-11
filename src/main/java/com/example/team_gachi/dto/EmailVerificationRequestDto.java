package com.example.team_gachi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerificationRequestDto {
    // 이메일 주소 변수
    private String email;
    // 이메일 인증 코드 변수
    private String code;

    public EmailVerificationRequestDto(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
