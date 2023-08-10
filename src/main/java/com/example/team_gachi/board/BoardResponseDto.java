package com.example.team_gachi.board;

import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String boardName;
    private String nickname;
    private String boardInfo;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.boardName = board.getBoardName();
        this.nickname = board.getNickname();
        this.boardInfo = board.getBoardInfo();
    }


}
