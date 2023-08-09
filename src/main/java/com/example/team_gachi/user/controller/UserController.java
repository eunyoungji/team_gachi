/*
package com.example.team_gachi.user.controller;

import com.example.team_gachi.service.UserService;
import com.example.team_gachi.user.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //post 요청으로 회원가입 프론트에 전달
    @PostMapping("/user/signup")
    @ResponseBody
    public String signup(@RequestBody SignupRequestDto requestDto, HttpServletResponse response) {
        userService.signup(requestDto);
        return "redirect:/user/login-page";

    }
}
*/

