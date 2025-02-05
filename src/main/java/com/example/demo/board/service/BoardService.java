package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Board findBoardByCode(long code) {
        Optional<Board> board = boardRepository.findById(code);
//        if(board.isEmpty())
//            return null;
        return board.orElse(null);
    }
}
