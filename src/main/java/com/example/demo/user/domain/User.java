package com.example.demo.user.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    private String username;
    private String password;
    private Timestamp regDate;
    private Timestamp modDate;

}
