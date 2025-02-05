package com.example.demo.controller;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.BoardInfo;
import com.example.demo.board.domain.BoardRequestDto;
import com.example.demo.board.domain.BoardResponseDto;
import com.example.demo.board.service.BoardInfoService;
import com.example.demo.board.service.BoardService;
import com.example.demo.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardRestController {

    private final BoardService boardService;
    private final BoardInfoService boardInfoService;

    @PostMapping("/write")
    public BoardRequestDto write(@RequestBody BoardRequestDto boardDto) {
        System.out.println("title : " + boardDto.getTitle());
        System.out.println("author : " + boardDto.getAuthor());
        System.out.println("content : " + boardDto.getContent());
        return boardDto;
    }

    @GetMapping("/detail/{code}")
    public ResponseEntity<ResponseDto> getBoardByCode(@PathVariable long code) {
        BoardInfo board = boardInfoService.findBoardInfoByCode(code);

        if(board == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND.value())
                    .body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시물입니다."));
        }

        BoardResponseDto boardDto = new BoardResponseDto(board);
        return ResponseEntity.ok(boardDto);
    }
}
