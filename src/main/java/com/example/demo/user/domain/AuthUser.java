package com.example.demo.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {

    private long code;
    private String username;
    private String nickname;

}
