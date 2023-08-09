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
    private Long columnOrder;
    private Board board;
    private List<Card> cardList;

    public ColumnResponseDto(ColumnClass column){
        this.id = column.getId();
        this.title = column.getTitle();
        this.columnOrder = column.getColumnOrder();
        this.board = column.getBoard();
    }
}
