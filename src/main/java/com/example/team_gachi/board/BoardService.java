package com.example.team_gachi.board;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 보드 생성
    public BoardResponseDto createBoard(BoardRequestDto requestDto ) {
        Board board = new Board(requestDto);
        Board saveBoard = boardRepository.save(board);
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        return boardResponseDto;
    }

    // 자신의 모든 보드 조회
    @Transactional(readOnly = true)
    public BoardResponseDto readAllBoard(BoardRequestDto requestDto) {
        try {
            List<Board> boards = boardRepository.findAllByUserByCreatedDesc(user);
            return BoardResponseDto.(boards);
        } catch (Exception e) {
            throw new RuntimeException("보드 조회 실패" + e.getMessage(), e);
        }

    }


    public Board findBoard(Long id) {
        return null; //추가하기
    }
}
