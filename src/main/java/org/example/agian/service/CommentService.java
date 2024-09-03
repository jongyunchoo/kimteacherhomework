package org.example.agian.service;

import lombok.RequiredArgsConstructor;
import org.example.agian.dto.Comment.CommentResponseDto;
import org.example.agian.dto.Comment.CommentSaveRequestDto;
import org.example.agian.dto.Comment.CommentSaveResponseDto;
import org.example.agian.entity.Board;
import org.example.agian.entity.Comment;
import org.example.agian.repository.BoardRepository;
import org.example.agian.repository.CommentRepository;
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

        Board board = boardRepository.findById(boardId).orElseThrow(()-> new NullPointerException("게시글 없습니다."));

        Comment newComment = new Comment(commentSaveRequestDto.getContents(), board);
        Comment savedCommnet = commentRepository.save(newComment);
        return new CommentSaveResponseDto(savedCommnet.getId(),savedCommnet.getContents());
    }

    public List<CommentResponseDto> getComments() {
        List<Comment> commentList = commentRepository.findAll();

        List<CommentResponseDto> dtoList = new ArrayList<>();
        for(Comment comment: commentList){
            dtoList.add(new CommentResponseDto(comment.getId(),comment.getContents()));
        }

        return dtoList;
    }
}
