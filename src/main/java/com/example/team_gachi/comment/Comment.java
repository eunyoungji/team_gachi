package com.example.team_gachi.comment;

import com.example.team_gachi.card.Card;
import com.example.team_gachi.card.CardRequestDto;
import com.example.team_gachi.column.ColumnClass;
import com.example.team_gachi.common.Timestamped;
import com.example.team_gachi.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="comment")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long commentId;

    @Column(name = "comment", nullable = false)
    String commentContent;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

//    @ManyToOne
//    @Column(name = "user_id", nullable = false)
//    private User user;


//    public Comment(CommentRequestDto commentRequestDto) { // , Card card
//        this.commentContent = commentRequestDto.getCommentContent();
//        //this.card = card;
//        //this.user = commentRequestDto.getUser();
//    }

    public Comment(String commentContent) {
        this.commentContent = commentContent;
    }


    public void updateComment(CommentRequestDto commentRequestDto) { //, Card card
        this.commentContent = commentRequestDto.getCommentContent();
        //this.card = card;
    }
}
