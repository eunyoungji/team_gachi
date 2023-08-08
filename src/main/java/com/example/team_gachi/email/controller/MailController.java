package com.example.team_gachi.email.controller;

import com.example.team_gachi.email.dto.CodeDto;
import com.example.team_gachi.email.dto.MailDto;
import com.example.team_gachi.email.service.MailService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequiredArgsConstructor
public class MailController {

    // 메일 인증
    @PostMapping("/user/mail")
    public String mailConfirm(@RequestBody MailDto mailDto) throws Exception {
        String code = mailService.sendSimpleMessage(mailDto.getEmail());
        System.out.println("인증코드 : " + code);
        return code;
    }

    // 인증받는 번호 비교
    // try-catch로 예외처리
    @PostMapping("/user/mailCode")
    public void mailCodeCompare(@RequestBody CodeDto codeDto, HttpServletResponse res){
        try{
            mailService.comparecode(codeDto);
        }
        catch (Exception e){
            res.setStatus(400);
        }

    }


}
