package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.BoardRepository;
import com.example.demo.board.domain.BoardRequestDto;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRepository;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public boolean createBoard(BoardRequestDto boardDto) {
        // 1. 유저 확인
        String username = boardDto.getAuthor();
        Long userCode = userRepository.findUserCodeByUsername(username);

        if(userCode == null)
            return false;

//        User user = userRepository.findUserByUsername(username);
//        if(user == null)
//            return false;

        // 2. 게시물 저장
        Board board = new Board(userCode, boardDto);
        boardRepository.save(board);
        return true;
    }

    public Board findBoardByCode(long code) {
        Optional<Board> board = boardRepository.findById(code);
//        if(board.isEmpty())
//            return null;
        return board.orElse(null);
    }

    @Transactional
    public boolean updateBoard(BoardRequestDto boardDto) {

        long code = boardDto.getCode();
        Optional<Board> target = boardRepository.findById(code);

        Board board = target.orElse(null);

        String username = boardDto.getAuthor();
        Long userCode = userRepository.findUserCodeByUsername(username);

        if(board == null || userCode == null || board.getAuthor() != userCode)
            return false;

        board.update(boardDto);
        return true;
    }

    @Transactional
    public boolean deleteBoardByCode(long code) {
        Optional<Board> target = boardRepository.findById(code);
        Board board = target.orElse(null);

        if(board == null)
            return false;

        boardRepository.delete(board);
        return true;
    }


}
