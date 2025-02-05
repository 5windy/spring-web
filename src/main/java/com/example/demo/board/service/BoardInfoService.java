package com.example.demo.board.service;

import com.example.demo.board.domain.BoardInfo;
import com.example.demo.board.domain.BoardInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardInfoService {

    private final BoardInfoRepository boardInfoRepository;

    public BoardInfo findBoardInfoByCode(long code) {
        Optional<BoardInfo> boardInfo = boardInfoRepository.findById(code);
        return boardInfo.orElse(null);
    }
}
