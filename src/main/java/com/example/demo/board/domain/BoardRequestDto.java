package com.example.demo.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    private long code;
    private String title;
    private String author;
    private String content;
    private MultipartFile file;

}
