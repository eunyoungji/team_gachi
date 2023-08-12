package com.example.team_gachi.comment;

import com.example.team_gachi.card.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    //private final CardServiceImpl cardService;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) { //, User user
        //Card card = cardService.findCard(cardRequestDto.getCardId());
        Comment comment = new Comment(commentRequestDto.getCommentContent());
        //comment.setUser(user);
        //comment.setCard(card);

        var savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    @Override
    public void deleteComment(Comment comment) { //, User user
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Comment comment, CommentRequestDto cardRequestDto) { //, User user

        comment.setCommentContent(cardRequestDto.getCommentContent());

        return new CommentResponseDto(comment);
    }

    @Override
    public Comment findComment(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }
}
