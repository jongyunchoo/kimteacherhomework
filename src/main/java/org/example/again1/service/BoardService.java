package org.example.again1.service;

import lombok.RequiredArgsConstructor;
import org.example.again1.dto.Board.*;
import org.example.again1.entity.Board;
import org.example.again1.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class BoardService {

    private BoardRepository boardRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board newBoard = new Board(boardSaveRequestDto.getTitle(),boardSaveRequestDto.getContents());
        Board savedBoard = boardRepository.save(newBoard);

        return new BoardSaveResponseDto(savedBoard.getId(),savedBoard.getTitle(),savedBoard.getContents());
    }

    public Page<BoardSimpleResponseDto> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size);

        Page<Board> boards = boardRepository.findAllByOredeByModifiedAtDesc(pageable);

        return boards.map(board -> new BoardSimpleResponseDto(board.getId(),board.getTitle(),board.getComments()));
    }

    @Transactional
    public BoardUpdateResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new NullPointerException("게시물 없습니다."));

        board.update(boardUpdateRequestDto.getTitle(),boardUpdateRequestDto.getContents());

        return new BoardUpdateResponseDto(board.getId(),board.getTitle(),board.getContents());
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        if(!boardRepository.existsById(boardId)){
            throw new NullPointerException("게시물 없습니다.");
        }

        boardRepository.deleteById(boardId);
    }



}
