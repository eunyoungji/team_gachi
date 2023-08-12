package com.example.team_gachi.comment;

import com.example.team_gachi.card.Card;
import com.example.team_gachi.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
public interface CommentService {
//    //private final CardService cardService;
//    private final CommentRepository commentRepository;
//
//    public ResponseEntity<CommentResponseDto> createComment(CommentRequestDto commentRequestDto) { //Long cardId,
//        //Card card = cardService.findCard(cardId);
//        Comment comment = new Comment(commentRequestDto); //, card
//        commentRepository.save(comment);
//        return new CommentResponseDto(comment);
//    }


    CommentResponseDto createComment(CommentRequestDto commentRequestDto); //,  User user
    CommentResponseDto updateComment(Comment comment, CommentRequestDto commentRequestDto); //, User user
    void deleteComment(Comment comment); // , User user
    Comment findComment(long commentId);

}
