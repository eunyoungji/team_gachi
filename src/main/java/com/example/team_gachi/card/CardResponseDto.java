package com.example.team_gachi.card;

import com.example.team_gachi.column.ColumnClass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String color;
    private LocalDateTime dueDate;
    private ColumnClass columns;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.color = card.getColor();
        this.dueDate = card.getDueDate();
        this.columns = card.getColumns();
    }
}
