package com.example.team_gachi.column;

import com.example.team_gachi.board.Board;
import com.example.team_gachi.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ColumnResponseDto {
    private Long id;
    private String title;
    private Board board;
    private List<Card> cardList;

    public ColumnResponseDto(ColumnClass column){
        this.id = column.getId();
        this.title = column.getTitle();
        this.board = column.getBoard();
    }
}
