package com.example.demo.controller;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRequestDto;
import com.example.demo.user.domain.UserResponseDto;
import com.example.demo.user.service.UserService;
import com.example.demo.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserRestController {

    private final UserService userService;

    @PostMapping("/singup")
    public UserRequestDto signup(@RequestBody UserRequestDto userDto) {
        System.out.println("username : " + userDto.getUsername());
        System.out.println("password : " + userDto.getPassword());
        return userDto;
    }

    @PostMapping("/signin")
    public UserRequestDto signin(@RequestBody UserRequestDto userDto) {
        System.out.println("username : " + userDto.getUsername());
        System.out.println("password : " + userDto.getPassword());
        return userDto;
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseDto> getUserByCode(@PathVariable long code) {
        UserResponseDto userDto = null;

        // UserService를 통한 UserRepository로의 데이터 요청 결과를 받아와서 처리
        User user = userService.findUserByCode(code);

        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "존재하지 않는 유저 정보입니다."));
        }

        userDto = new UserResponseDto(user);
//        return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(user));
        return ResponseEntity.ok(new UserResponseDto(user));
    }

}
