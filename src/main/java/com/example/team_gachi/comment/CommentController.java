package com.example.team_gachi.comment;

import com.example.team_gachi.common.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    //private final CardService cardService;

    // 코멘트 생성
    //@PostMapping("/columns/{columnId}/cards/{cardId}/comments")
    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto){ //@PathVariable Long cardId,
        CommentResponseDto result = commentService.createComment(commentRequestDto); //
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto){
        ////Card card = cardService.findCard(cardId);
        //Comment comment = commentService.findComment(commentId);
        //CommentResponseDto result = comment.updateComment(comment, commentRequestDto);
        //return ResponseEntity.ok().body(result);

        try {
            Comment comment = commentService.findComment(commentId);
            commentService.updateComment(comment, commentRequestDto); // , userDetails.getUser()
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 수정 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 카드 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long commentId){
        ////Card card = cardService.findCard(cardId);
        //Comment comment = commentService.findComment(commentId);
        //commentService.deleteComment(comment);
        //return ResponseEntity.ok().body(HttpStatus.OK.value());

        try {
            Comment comment = commentService.findComment(commentId);
            commentService.deleteComment(comment); //, userDetails.getUser()
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
