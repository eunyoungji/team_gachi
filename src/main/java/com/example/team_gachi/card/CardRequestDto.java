package com.example.team_gachi.card;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardRequestDto {
    private String title;
    private String content;
    private String color;
    private String dueDate;
    //private User user;

    @Builder
    //@Getter
    //@Setter
    public CardRequestDto(String title, String content, String color, String dueDate) {
    //public class CardRequestDto {
        this.title = title;
        this.content = content;
        this.color = color;
        this.dueDate = dueDate;
        //this.user = user;
    }
}
