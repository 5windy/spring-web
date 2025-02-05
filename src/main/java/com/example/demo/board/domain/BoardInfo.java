package com.example.demo.board.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Entity
@Table(name ="board_view")
public class BoardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    private String author;
    private String title;
    private String content;
    private Timestamp regDate;
    private Timestamp modDate;
}
