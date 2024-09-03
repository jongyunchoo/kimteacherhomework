package org.example.again1.service;

import lombok.RequiredArgsConstructor;
import org.example.again1.dto.Comment.CommentResponseDto;
import org.example.again1.dto.Comment.CommentSaveRequestDto;
import org.example.again1.dto.Comment.CommentSaveResponseDto;
import org.example.again1.entity.Board;
import org.example.again1.entity.Comment;
import org.example.again1.repository.BoardRepository;
import org.example.again1.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(Long boardId, CommentSaveRequestDto commentSaveRequestDto) {

        Board board = boardRepository.findById(boardId).orElseThrow(()->new NullPointerException("게시물 없습니다."));

        Comment newComment = new Comment(commentSaveRequestDto.getContents(), board);
        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(savedComment.getId(),savedComment.getContents());
    }

    public List<CommentResponseDto> getComments() {
        List<Comment> commentList =commentRepository.findAll();

        List<CommentResponseDto> dtoList = new ArrayList<>();
        for(Comment comment : commentList){
            dtoList.add(new CommentResponseDto(comment.getId(),comment.getContents()));
        }

        return dtoList;
    }
}
