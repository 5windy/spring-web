package com.example.demo.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String username;
    private String password;

//    public UserRequestDto() {
//
//    }

//    public UserRequestDto(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

//    public String getUsername() {
//        return this.username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return this.password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

}
