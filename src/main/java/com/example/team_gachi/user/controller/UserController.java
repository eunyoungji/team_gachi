package com.example.team_gachi.user.controller;

import com.example.team_gachi.dto.*;
import com.example.team_gachi.security.UserDetailsImpl;
import com.example.team_gachi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //getmapping으로 로그인페이지 html 전달.
    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    //getmapping으로 회원가입 페이지 html 전달.

    @PostMapping("/user/signup")
    public String signup(@RequestBody @Valid SignupRequestDto requestDto){
        userService.signup(requestDto);

        return "redirect:/api/users/signup";
    }

    @GetMapping("/user/profile")
    public ResponseEntity<ApiResponseDto> getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        try {
            return ResponseEntity.status(200).body(userService.getUserProfile(userDetails.getUser(), response));
        }catch (IllegalArgumentException ex){
            response.setStatus(400);
            return ResponseEntity.status(400).body(new ApiResponseDto(ex.getMessage(), response.getStatus()));
        }
    }

    // 프로필 변경
    @PutMapping("/user/profile")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> modifyUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserProfileRequestDto requestDto, HttpServletResponse response) {
        try {
            return ResponseEntity.status(201).body(userService.modifyUserProfile(userDetails.getUser(), requestDto, response));
        } catch (IllegalArgumentException ex) {
            response.setStatus(400);
            return ResponseEntity.status(400).body(new ApiResponseDto(ex.getMessage(), response.getStatus()));
        }
    }

    // 비밀번호 변경
    @PutMapping("/user/profile/pw")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> modifyUserPassword(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserPasswordRequestDto requestDto, HttpServletResponse response) {
        try {
            return ResponseEntity.status(201).body(userService.modifyUserPassword(userDetails.getUser(), requestDto, response));
        } catch (IllegalArgumentException ex) {
            response.setStatus(400);
            return ResponseEntity.status(400).body(new ApiResponseDto(ex.getMessage(), response.getStatus()));
        }
    }

    // 이메일 정보로 인증번호 발급.
    @PostMapping("/user/signup/authentication")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> authentication(@RequestBody AuthenticationRequestDto requestDto, HttpServletResponse response){
        try {
            return ResponseEntity.status(200).body(userService.authentication(requestDto, response));
        }
        catch (IllegalArgumentException ex) {
            response.setStatus(400);
            return ResponseEntity.status(400).body(new ApiResponseDto(ex.getMessage(), response.getStatus()));
        }
    }
    // 입력한 이메일과 인증번호를 받아 DB 내 인증번호와 대조 및 만료여부 검증
    @PostMapping("/user/signup/verification")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> verification(@RequestBody VerificationRequestDto requestDto, HttpServletResponse response){
        try {
            return ResponseEntity.status(200).body(userService.verification(requestDto, response));
        }
        catch (IllegalArgumentException ex) {
            response.setStatus(400);
            return ResponseEntity.status(400).body(new ApiResponseDto(ex.getMessage(), response.getStatus()));
        }
    }

    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
        } catch (Exception e) {
            // login 페이지에 에러 메시지 전달.
            return "redirect:/api/user/login-page?error";
        }
        return "redirect:/";//  main으로 redirect
    }

}