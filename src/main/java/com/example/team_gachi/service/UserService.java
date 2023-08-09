package com.example.team_gachi.service;

import com.example.team_gachi.jwt.JwtUtil;
import com.example.team_gachi.user.dto.SignupRequestDto;
import com.example.team_gachi.user.entity.User;
import com.example.team_gachi.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

    }

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUserid();
        String password = passwordEncoder.encode(requestDto.getPassword());


        // 회원 중복여부 확인.
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("사용자가 이미 존재합니다.");
        }

        //  닉네임 중복여부 확인.
        String nickname = requestDto.getNickname();
        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if(checkNickname.isPresent()){
            throw new IllegalArgumentException("이미 있는 닉네임입니다.");
        }

        User user = new User(username,password,nickname);
    }
}
