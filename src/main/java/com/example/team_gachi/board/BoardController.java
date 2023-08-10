package com.example.team_gachi.board;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;


    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return null;
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto mainBoard(@RequestBody BoardRequestDto requestDto) {
        return null;
    }

    @PutMapping("/board/{id}")
    public Long updateBoard (@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
    return null;
    }

    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        return null;
    }




}

