package com.example.demo.controller;

import com.example.demo.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    // 요청 단에서 데이타 가져오기

    // 1) @PathVariable
    // ㄴ /users/signup/{username}/{password}
//    @PostMapping("/singup/{username}/{password}")
//    public void signup(@PathVariable String username, @PathVariable String password) {
//        System.out.println("username : " + username);
//        System.out.println("password : " + password);
//    }

    // 2) @RequestParam
    // ㄴ /users/signup?username=&password=
//    @PostMapping("/singup")
//    public void signup(@RequestParam String username, @RequestParam String password) {
//        System.out.println("username : " + username);
//        System.out.println("password : " + password);
//    }

    // 3) @RequestBody
    // ㄴ /users/signup
    // ㄴ JSON {
    //    "username" : "",
    //    "password" : ""
    // }
//    @ResponseBody
//    @PostMapping("/singup")
//    public UserRequestDto signup(@RequestBody UserRequestDto userDto) {
//        System.out.println("username : " + userDto.getUsername());
//        System.out.println("password : " + userDto.getPassword());
//        return userDto;
//    }

    @GetMapping("/signin")
    public String signin() {
        return "user/signin";
    }

}
