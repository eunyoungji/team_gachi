package com.example.team_gachi.column;

import com.example.team_gachi.board.Board;
import com.example.team_gachi.card.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "column_class")
public class ColumnClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Long columnIndex;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "column")
    private List<Card> cardList;

    public ColumnClass(ColumnRequestDto columnRequestDto, Board board) {
        this.title = columnRequestDto.getTitle();
        this.board = board;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setIndex(){
        this.columnIndex = this.getId();
    }

    public void setIndexBySort(Long sort){
        this.columnIndex = sort;
    }

    public void addCard(Card card) {
        this.cardList.add(card);
    }
}
