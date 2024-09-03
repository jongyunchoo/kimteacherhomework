package org.example.again1.dto.Comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String contents;

    public CommentResponseDto(Long id, String contents){
        this.id = id;
        this.contents = contents;
    }
}
