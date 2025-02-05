package com.example.demo.board.service;

import com.example.demo.board.domain.BoardInfo;
import com.example.demo.board.domain.BoardInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardInfoService {

    private final BoardInfoRepository boardInfoRepository;

    public List<BoardInfo> findBoardInfoAll(Pageable pageable) {
        List<BoardInfo> boards = boardInfoRepository.findAll(pageable).getContent();
        return boards;
    }

    public BoardInfo findBoardInfoByCode(long code) {
        Optional<BoardInfo> boardInfo = boardInfoRepository.findById(code);
        return boardInfo.orElse(null);
    }
}
