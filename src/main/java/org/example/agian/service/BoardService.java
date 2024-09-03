package org.example.agian.service;

import lombok.RequiredArgsConstructor;
import org.example.agian.dto.Board.*;
import org.example.agian.entity.Board;
import org.example.agian.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board newBoard = new Board(boardSaveRequestDto.getTitle(),boardSaveRequestDto.getContents());

        Board saveBoard = boardRepository.save(newBoard);

        return new BoardSaveResponseDto(saveBoard.getId(),saveBoard.getTitle(),saveBoard.getContents());
    }

    public Page<BoardSimpleResponseDto> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size);

        Page<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc(pageable);

        return boards.map(board -> new BoardSimpleResponseDto(board.getId(),board.getTitle(),board.getComments()));

    }
    
    @Transactional
    public BoardUpdateResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(()->new NullPointerException("게시글은 없습니다."));
        board.update(boardUpdateRequestDto.getTitle(),boardUpdateRequestDto.getContents());

        return new BoardUpdateResponseDto(board.getId(),board.getTitle(),board.getContents());
    }

    public void deleteBoard(Long boardId) {
        if(!boardRepository.existsById(boardId)){
            throw new NullPointerException("게시글 없습니다.");
        }
        boardRepository.deleteById(boardId);
    }
}
