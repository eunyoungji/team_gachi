package com.example.team_gachi.service;

import com.example.team_gachi.common.ApiResponseDto;
import com.example.team_gachi.dto.*;
import com.example.team_gachi.jwt.JwtUtil;
import com.example.team_gachi.user.entity.User;
import com.example.team_gachi.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
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

/*
        UserRoleEnum role= UserRoleEnum.USER;
        if(requestDto.isAdmin()){
            if(ADMIN_TOKEN.equals(requestDto.getAdminToken())){
                throw new IllegalArgumentException("관리자의 암호가 틀립니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
 */
        // 사용자를 등록.
        User user = new User(username, password, nickname);
        userRepository.save(user);
    }


        // 프로필을 변경하기 위한 메서드

        @Transactional
        public ApiResponseDto modifyUserProfile(User user, UserProfileRequestDto requestDto, HttpServletResponse response){
            User usertarget = findUser(user.getUsername());

            if(usertarget!=null ){
                usertarget.modifyProfile(requestDto);
                response.setStatus(201);
                return new ApiResponseDto("해당 사항으로 프로필 변경이 완료되었습니다.", response.getStatus());
            }else {
                throw new IllegalArgumentException("아이디는 존재하지 않습니다.");
            }
        }


        // 비밀번호를 변경하는 메서드

    @Transactional
    public ApiResponseDto modifyUserPassword(User user, UserPasswordRequestDto requestDto, HttpServletResponse response){

        User targetUser = findUser(user.getUsername());
        if(targetUser!= null){
            if(passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
                response.setStatus(201);
                targetUser.modifyPassword(passwordEncoder.encode(requestDto.getModifyPassword()));
            }else {
                throw new IllegalArgumentException(" 비밀번호가 틀립니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 사용자는 존재하지 않습니다.");
        }
        return new ApiResponseDto("비밀번호 변경이 성공적으로 완료되었습니다.", response.getStatus());
    }



    // 회원가입을 위한 이메일 인증
    @Transactional
    public ApiResponseDto authentication(AuthenticationRequestDto requestDto, HttpServletResponse response){
        signupAuthRepository.deleteExpiredSignupAuth();

        //1. 이미 가입된 이메일이 있는 지 확인.
        User targetUser = userRepository.findByEmail(requestDto.getEmail()).orElse(null);

        if(targetUser!=null){
            throw new IllegalArgumentException("이미 가입되어있는 사용자의 이메일입니다. 다른 이메일을 입력해주시기 바랍니다.");

        }

        // 2. 이전에 인증번호 요청 이력 여부 확인 -> 있을 시 , 삭제 진행
        SignupAuth targetSignupAuth = signupAuthRepository.findByEmail(requestDto.getEmail()).orElse(null);

        if(targetSignupAuth!=null){
            signupAuthRepository.delete(targetSignupAuth);
        }

        // 3. 정상적으로 인증번호 발급, 메일 보내기.
        String authkKey = getTempCode();
        authenticationEmailSend(authKey, requestDto.getEmail());

        //4. DB에 이메일정보, 인증키 저장.

        SignupAuth signupAuth = new SignupAuth(requestDto.getEmail(), authkKey);
        signupAuthRepository.save(signupAuth);
        return new ApiResponseDto("해당 이메일로 인증번호 전송을 완료했습니다." , response.getStatus());

    }

    // 회원가입을 위한 인증번호 이메일전송 메서드

    private void authenticationEmailSend(String authkey, String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Gachi 회원가입 이메일 인증번호");
        message.setTest("반갑습니다! Gachi에서 전송한 이메일 인증코드입니다.\n 인증번호:" ,authkey );
        mailSender.send(message);
    }

    @Transactional
    public ApiResponseDto verification(VerificationRequestDto requestDto, HttpServletResponse response){
        // db에 있는 이메일 및 인증번호 가져오기.
        SignupAuth targetSignupAuth = signupAuthRepository.findByEmail(requestDto.getEmail()).orElse(null);
        if(targetSignupAuth == null){
            throw new IllegalArgumentException("아직 인증번호가 발급되지 않았습니다. 인증번호 발급을 먼저 해주세요.");
        }

        // 인증번호 대조
        //1. 인증번호 일치
        if(targetSignupAuth.getAuthkey().equals(requestDto.getAuthkey())){
            System.out.println("인증번호가 일치!");
            targetSignupAuth.changeStatusOK();  // 인증번호의 상태값을 변경.
            signupAuthRepository,save(targetSignupAuth);    // db에 저장.
            return new ApiResponseDto(" 인증번호의 확인이 완료되었습니다.", response.getStatus());
        }
        //2. 인증번호 불일치
        else {
            targetSignupAuth.changeStatusNO();  // 상태값을 0으로
            signupAuthRepository,save(targetSignupAuth);    // db에 저장.
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다!");
        }

    }

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
