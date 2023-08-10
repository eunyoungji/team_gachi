package com.example.team_gachi.service;

import com.example.team_gachi.common.ApiResponseDto;
import com.example.team_gachi.dto.UserProfileRequestDto;
import com.example.team_gachi.jwt.JwtUtil;
import com.example.team_gachi.repository.SignupAuthRepository;
import com.example.team_gachi.user.dto.LoginRequestDto;
import com.example.team_gachi.user.dto.SignupRequestDto;
import com.example.team_gachi.user.entity.User;
import com.example.team_gachi.user.entity.UserRoleEnum;
import com.example.team_gachi.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // 회원가입을 위한 메서드.
    // 요청받는 requestbody의 정보 (username, password, nickname)으로 계정생성.
    public void signup(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();


        // 회원 중복여부 확인.
        Optional<User> checkUsername = userRepository.findByUsername(username);
        //  닉네임 중복여부 확인.
        Optional<User> checkNickname = userRepository.findByNickname(nickname);

        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("사용자가 이미 존재합니다.");
        }

        if(checkNickname.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }


        UserRoleEnum role= UserRoleEnum.USER;
        if(requestDto.isAdmin()){
            if(!ADMIN_TOKEN.equals(requestDto.getAdminToken())){
                throw new IllegalArgumentException("관리자의 암호가 틀립니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        // 사용자를 등록.
        User user = new User(username, password,nickname);
    }

/*
    // 프로필을 변경하기 위한 메서드

    @Transactional
    public ApiResponseDto modifyUserProfile(User user, UserProfileRequestDto requestDto, HttpServletResponse response){
        User usertarget = findUser(user.getUserId());

        if(usertarget!=null ){
            usertarget.modifyProfile(requestDto);
            response.setStatus(201);
            return new ApiResponseDto("해당 사항으로 프로필 변경이 완료되었습니다.", response.getStatus());
        }else {
            throw new IllegalArgumentException("아이디는 존재하지 않습니다.");
        }
    }

 */
    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자인지 확인.

        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException(" 가입된 사용자가 없습니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }

        // jwt생성, 쿠키 저장, response객체 추가.
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token,res);
    }
}
