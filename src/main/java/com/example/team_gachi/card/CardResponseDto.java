package com.example.team_gachi.card;



import com.example.team_gachi.column.ColumnClass;
import com.example.team_gachi.column.ColumnResponseDto;
import com.example.team_gachi.comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String color;
    private String dueDate;
    private ColumnClass column;
    //private List<ColumnResponseDto> columns;
    //private List<CommentResponseDto> comments;
    //private List<CardRequestDto> cardsList;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.color = card.getColor();
        this.dueDate = card.getDueDate();
        this.column = card.getColumn();
        //this.columns = card.getColumns().stream().map(ColumnResponseDto::new).toList();
        //this.comments = card.getComments().stream().map(CommentResponseDto::new).toList();
    }
}
