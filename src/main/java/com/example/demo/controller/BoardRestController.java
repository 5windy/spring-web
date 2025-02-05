package com.example.demo.controller;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.BoardInfo;
import com.example.demo.board.domain.BoardRequestDto;
import com.example.demo.board.domain.BoardResponseDto;
import com.example.demo.board.service.BoardInfoService;
import com.example.demo.board.service.BoardService;
import com.example.demo.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardRestController {

    private final BoardService boardService;
    private final BoardInfoService boardInfoService;

    // 요청 단에서 데이터 가져오기

    // 4) @RequestPart
//    @PostMapping(value = "/write", consumes = "multipart/form-data")
//    public ResponseEntity<ResponseDto> write(@RequestPart String title, @RequestPart String author, @RequestPart String content, @RequestPart MultipartFile file) {
//        BoardRequestDto boardDto = new BoardRequestDto();
//        boardDto.setTitle(title);
//        boardDto.setAuthor(author);
//        boardDto.setContent(content);
//
//        System.out.println("file : " + file.getOriginalFilename());
//
//        boolean isSuccess = boardService.createBoard(boardDto);
//        if(!isSuccess)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
//                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "사용자 정보가 부정확합니다."));
//
//        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글이 성공적으로 저장되었습니다."));
//    }

    // 5) @ModelAttribute
    @PostMapping(value = "/write", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDto> write(@ModelAttribute BoardRequestDto boardDto) {
        System.out.println("file : " + boardDto.getFile().getOriginalFilename());

        boolean isSuccess = boardService.createBoard(boardDto);
        if(!isSuccess)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "사용자 정보가 부정확합니다."));

        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글이 성공적으로 저장되었습니다."));
    }

//    @PostMapping("/write")
//    public ResponseEntity<ResponseDto> write(@RequestBody BoardRequestDto boardDto) {
//        boolean isSuccess = boardService.createBoard(boardDto);
//        if(!isSuccess)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
//                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "사용자 정보가 부정확합니다."));
//
//        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글이 성공적으로 저장되었습니다."));
//    }

    @GetMapping("/all")
    public ResponseEntity<?> getBoardAll(@PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) Integer page) {
        // 1. 기본 페이징
//        List<BoardInfo> list = boardInfoService.findBoardInfoAll(pageable);

        // 2. 페이지 설정
//        Pageable paging = pageable.withPage(page == null ? 0 : page - 1);

        // 3. 매뉴얼 설정
        Pageable paging = PageRequest.of(page == null ? 0 : page - 1, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        List<BoardInfo> list = boardInfoService.findBoardInfoAll(paging);

        if(list.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                    .body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "존재하지 않는 페이지입니다."));

        return ResponseEntity.ok(list);
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

    @PutMapping({"", "/"})
    public ResponseEntity<ResponseDto> updateBoard(@RequestBody BoardRequestDto boardDto) {
        boolean isSuccess = boardService.updateBoard(boardDto);
        if(!isSuccess)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "유효하지 않은 정보입니다."));
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글 수정이 완료되었습니다."));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ResponseDto> deleteBoardByCode(@PathVariable long code) {
        boolean isSuccess = boardService.deleteBoardByCode(code);
        if(!isSuccess)
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                    .body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시물입니다."));
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글 삭제가 완료되었습니다."));
    }
}
