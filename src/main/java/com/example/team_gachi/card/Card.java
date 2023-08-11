package com.example.team_gachi.card;


import com.example.team_gachi.column.ColumnClass;
import com.example.team_gachi.comment.Comment;
//import com.example.team_gachi.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "content", nullable = false)
    String content;

    @Column(name = "color")
    String color;

    @Column(name = "dueDate")
    String dueDate;

    @Column
    private Long cardIndex;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private ColumnClass column;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<ColumnClass> columns = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
//    private List<User> users = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();



    public Card(CardRequestDto cardRequestDto, ColumnClass column) {
        this.title = cardRequestDto.getTitle();
        this.content = cardRequestDto.getContent();
        this.color = cardRequestDto.getColor();
        this.dueDate = cardRequestDto.getDueDate();
        this.column = column;
    }


    public void updateCard(CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.content = cardRequestDto.getContent();
        this.color = cardRequestDto.getColor();
        this.dueDate = cardRequestDto.getDueDate();
    }
}
