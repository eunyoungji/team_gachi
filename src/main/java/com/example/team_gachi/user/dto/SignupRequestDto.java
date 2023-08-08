package com.example.team_gachi.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    @NotNull(message = "id 입력은 필수입니다. id를 입력해주세요.")
    @Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+",message = "id 형식이 맞지 않습니다.")
    String id;

    @NotNull(message = "password 입력은 필수입니다. password를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()]*$", message = "password는 알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 입력 가능합니다.")
    String password;


    String nickname;



}
