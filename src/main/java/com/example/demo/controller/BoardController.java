package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/boards")
@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String list() {
        return "board/list";
    }

    @GetMapping("/write")
    public String wirte() {
        return "board/writeForm";
    }

    // 요청 리소스의 패스값 얻기
    @GetMapping("/{code}")
    public String board(@PathVariable int code) {
        System.out.println("code : " + code);
        return "board/board";
    }

}
