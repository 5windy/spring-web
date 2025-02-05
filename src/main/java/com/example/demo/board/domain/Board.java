package com.example.demo.board.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Entity
@Table(name ="boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    private long author;
    private String title;
    private String content;
    private Timestamp regDate;
    private Timestamp modDate;

}
