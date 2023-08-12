package com.example.team_gachi.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long cardId;
    private String commentContent;
}
