package com.example.demo.board.domain;

import com.example.demo.util.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name ="boards")
public class Board extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    private long author;
    private String title;
    private String content;

    public Board(long userCode, BoardRequestDto boardDto) {
        this.author = userCode;
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }

    public void update(BoardRequestDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }

}
