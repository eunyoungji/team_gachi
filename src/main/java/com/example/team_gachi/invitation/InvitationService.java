package com.example.team_gachi.invitation;

import com.example.team_gachi.board.Board;
import com.example.team_gachi.board.BoardRepository;
import com.example.team_gachi.board.BoardResponseDto;
import com.example.team_gachi.security.UserDetailsImpl;
import com.example.team_gachi.user.entity.User;
import com.example.team_gachi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final UserRepository userRepository;
    private final com.example.team_gachi.invitation.InvitationRepository invitationRepository;
    private final BoardRepository boardRepository;

    public void inviteUser(UserDetailsImpl userDetails, InvitationRequestDto invitationRequestDto) {
        User invitedUser = userDetails.getUser();

        if (invitedUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        User invitingUser = userRepository.findByUsername(invitationRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        if (invitedUser.getUsername().equals(invitingUser.getUsername())) {
            throw new IllegalArgumentException("본인을 초대할 수 없습니다.");
        }

        if (invitationRepository.findByInvitedUserAndInvitingUser(invitedUser, invitingUser).isPresent()) {
            throw new IllegalArgumentException("이미 초대했습니다.");
        }

        invitationRepository.save(new Invitation(invitingUser, invitedUser));
    }

    public void rejectInvitation(UserDetailsImpl userDetails, InvitationRequestDto invitationRequestDto) {
        User invitedUser = userDetails.getUser();
        User invitingUser = userDetails.getUser();

        if (invitingUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        User followingUser = userRepository.findByUsername(invitationRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Invitation invitation = invitationRepository.findByInvitedUserAndInvitingUser(invitedUser, invitingUser)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 초대하지 않았습니다."));

        invitationRepository.delete(invitation);
    }

    public List<BoardResponseDto> readInvitedBoardList(User user) {
        List<BoardResponseDto> boardList = new ArrayList<>();
        List<Invitation> invitationList = invitationRepository.findAllByInvitedUser(user);
        List<User> userList = new ArrayList<>();

        for(Invitation invitation : invitationList) {
            userList.add(invitation.getInvitingUser());
        }

        for(User foundUser : userList) {
            List<Board> foundBoardList = boardRepository.findAllByUser(foundUser);
            boardList.addAll(foundBoardList.stream().map(BoardResponseDto :: new).toList());
        }

        return boardList;
    }
}