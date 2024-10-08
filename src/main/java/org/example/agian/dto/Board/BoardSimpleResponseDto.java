package org.example.agian.dto.Board;

import lombok.Getter;
import org.example.agian.dto.Comment.CommentResponseDto;
import org.example.agian.entity.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardSimpleResponseDto {

    private final Long id;
    private final String title;
    private final List<CommentResponseDto> comments;

    public BoardSimpleResponseDto(Long id, String title, List<Comment> comments){
        List<CommentResponseDto> dtoList = new ArrayList<>();
        for(Comment comment : comments){
            dtoList.add(new CommentResponseDto(comment.getId(),comment.getContents()));

        }
        this.id= id;
        this.title =title;
        this.comments = dtoList;
    }

}
