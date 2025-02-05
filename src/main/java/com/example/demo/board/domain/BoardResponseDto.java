package com.example.demo.board.domain;

import com.example.demo.util.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto extends ResponseDto {

    private long code;
    private String author;
    private String title;
    private String content;
    private Timestamp regDate;
    private Timestamp modDate;

    public BoardResponseDto(BoardInfo board) {
        this.code = board.getCode();
        this.author = board.getAuthor();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.regDate = board.getRegDate();
        this.modDate = board.getModDate();
        super.setStatusCode(HttpStatus.OK.value());
        super.setMessage("게시물이 성공적으로 조회되었습니다.");
    }

}
