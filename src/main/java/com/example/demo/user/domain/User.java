package com.example.demo.user.domain;

import com.example.demo.util.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    private String username;
    private String password;
    private String nickname;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void update(UserRequestDto userDto) {
        this.nickname = userDto.getNickname();
    }

}
