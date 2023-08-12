package com.example.team_gachi.invitation;

import com.example.team_gachi.common.ApiResponseDto;
import com.example.team_gachi.security.UserDetailsImpl;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invite")
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;
    @PostMapping("/inviting")
    public ResponseEntity<ApiResponseDto> inviteUser (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody InvitationRequestDto invitationRequestDto) {
        try {
            invitationService.inviteUser(userDetails, invitationRequestDto);
        } catch (DuplicateRequestException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new ApiResponseDto("보드 초대 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/rejecting")
    public ResponseEntity<ApiResponseDto> rejectInvitation (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody InvitationRequestDto invitationRequestDto) {
        try {
            invitationService.rejectInvitation(userDetails, invitationRequestDto);
        } catch (DuplicateRequestException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new ApiResponseDto("보드 초대 거절", HttpStatus.OK.value()));
    }

}