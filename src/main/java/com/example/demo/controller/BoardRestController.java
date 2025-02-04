package com.example.demo.controller;

import com.example.demo.board.domain.BoardRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/boards")
@RestController
public class BoardRestController {

    @PostMapping("/write")
    public BoardRequestDto write(@RequestBody BoardRequestDto boardDto) {
        System.out.println("title : " + boardDto.getTitle());
        System.out.println("author : " + boardDto.getAuthor());
        System.out.println("content : " + boardDto.getContent());
        return boardDto;
    }
}
