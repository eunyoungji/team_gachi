package com.example.team_gachi.user.controller;

import com.example.team_gachi.service.UserService;
import com.example.team_gachi.dto.LoginRequestDto;
import com.example.team_gachi.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto){
        userService.signup(requestDto);

        return "redirect:/api/users/signup";
    }
    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
        } catch (Exception e) {
            // login 페이지에 에러 메시지 전달.
            return "redirect:/api/user/login-page?error";
        }
        return "redirect:/";//  main으로 redirect
    }

}