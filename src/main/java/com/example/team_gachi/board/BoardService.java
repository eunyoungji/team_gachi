package com.example.team_gachi.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 보드가 존재하지 않습니다."));
    }
}
