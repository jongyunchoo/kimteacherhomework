package org.example.again2.controller;

import lombok.RequiredArgsConstructor;
import org.example.again2.dto.Comment.CommentResponseDto;
import org.example.again2.dto.Comment.CommentSaveRequestDto;
import org.example.again2.dto.Comment.CommentSaveResponseDto;
import org.example.again2.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public CommentSaveResponseDto saveComment(@PathVariable Long boardId, @RequestBody CommentSaveRequestDto commentSaveRequestDto){
        return commentService.saveComment(boardId,commentSaveRequestDto);
    }

    @GetMapping("/boards/comments")
    public List<CommentResponseDto> getComments(){
        return commentService.getComments();
    }
}
