package com.example.demo.controller;

import com.example.demo.board.domain.BoardInfo;
import com.example.demo.board.service.BoardInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@Controller
public class BoardController {

    private final BoardInfoService boardInfoService;

//    @GetMapping({"", "/"})
//    public String list(Model model, Pageable pageable, @RequestParam(required = false) Integer page) {
//        Pageable paging = PageRequest.of(page == null ? 0 : page - 1, 5);
//
//        List<BoardInfo> boards = boardInfoService.findBoardInfoAll(paging);
//        model.addAttribute("boards", boards);
//
//        int totalPages = boardInfoService.getTotalPages(paging);
//        model.addAttribute("totalPages", totalPages);
//
//        return "board/list";
//    }

    @GetMapping({"", "/"})
    public ModelAndView list(Pageable pageable, @RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("board/list");

        Pageable paging = PageRequest.of(page == null ? 0 : page - 1, 5);

        List<BoardInfo> boards = boardInfoService.findBoardInfoAll(paging);
        modelAndView.addObject("boards", boards);

        int totalPages = boardInfoService.getTotalPages(paging);
        modelAndView.addObject("totalPages", totalPages);

        return modelAndView;
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
