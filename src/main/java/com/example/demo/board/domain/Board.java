package com.example.demo.board.domain;

import com.example.demo.util.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name ="boards")
public class Board extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    private long author;
    private String title;
    private String content;

}
