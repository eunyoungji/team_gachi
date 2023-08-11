package com.example.team_gachi.jwt;

import com.example.team_gachi.common.ApiResponseDto;
import com.example.team_gachi.dto.LoginRequestDto;
import com.example.team_gachi.security.UserDetailsImpl;
import com.example.team_gachi.user.entity.UserRoleEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //requestBody 요청 값을 LoginRequestDto 객체타입으로 변환
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            //아이디, 비밀번호 입력내용을 체크.
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String userId = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        //사용자 ID/권한으로 JWT 생성
        String token = jwtUtil.createToken(userId, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token); //헤더에 Authorztion = Bearer {JWT} 추가

        //client에 응답하기 "message" = "로그인 성공"/ "status" = "200"
        ApiResponseDto apiResponseDto = new ApiResponseDto("로그인 성공", response.getStatus());
        String jsonResponseBody = new ObjectMapper().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true).writeValueAsString(apiResponseDto);//한글깨짐문제 방지
        response.setContentType("application/json"); //contenttype : json방식으로 응답 전달
        response.getWriter().write(jsonResponseBody);
        response.getWriter().flush();
        response.getWriter().close();
    }

    //인증 실패(로그인 실패)시 동작하는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException{
        response.setStatus(400);

        //client에 응답하기 "message" = "로그인 실패"/ "status" = "400"
        ApiResponseDto apiResponseDto = new ApiResponseDto("로그인 실패", response.getStatus());
        String jsonResponseBody = new ObjectMapper().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true).writeValueAsString(apiResponseDto);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponseBody);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
