package org.example.agian.controller;

import lombok.RequiredArgsConstructor;
import org.example.agian.dto.Comment.CommentResponseDto;
import org.example.agian.dto.Comment.CommentSaveRequestDto;
import org.example.agian.dto.Comment.CommentSaveResponseDto;
import org.example.agian.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public CommentSaveResponseDto saveComment(@PathVariable Long boardId, @RequestBody CommentSaveRequestDto commentSaveRequestDto){
        return commentService.saveComment(boardId, commentSaveRequestDto);
    }

    @GetMapping("/boards/comments")
    public List<CommentResponseDto> getComments(){
        return commentService.getComments();
    }
}
