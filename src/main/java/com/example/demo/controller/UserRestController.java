package com.example.demo.controller;

import com.example.demo.user.domain.AuthUser;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRequestDto;
import com.example.demo.user.domain.UserResponseDto;
import com.example.demo.user.service.UserService;
import com.example.demo.util.ResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserRestController {

    private final UserService userService;

    // /users
    // /users/
    @GetMapping("/valid/username")
    public ResponseEntity<ResponseDto> checkUserIsExisted(@RequestParam("value") String username) {
        User user = userService.findUserByUsername(username);

        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                    .body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "존재하지 않는 사용자입니다."));

        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "확인되는 사용자입니다."));
    }

    @GetMapping("/valid/nickname")
    public ResponseEntity<?> checkNickIsExisted(@RequestParam("value") String nickname) {
        User user = userService.findUserByNickname(nickname);

        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                    .body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "존재하지 않는 사용자입니다."));

        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "확인되는 사용자입니다."));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserRequestDto userDto) {
        // 1. 요청 객체로부터 필요한 정보 얻기
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String nickname = userDto.getNickname();

        // 2. 데이터 삽입 요청을 하기 위한 Entity 객체 생성
        User user = new User(username, password, nickname);

        // 3. 서비스가 제공하는 삽입 처리 메소드 호출을 통해 저장
        boolean isSuccess = userService.createUser(user);

        if(!isSuccess)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST.value())
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "중복 아이디는 가입이 불가합니다."));

        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "회원가입 요청이 완료되었습니다."));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> signin(@RequestBody UserRequestDto userDto, HttpSession session) {

        String username = userDto.getUsername();
        String password = userDto.getPassword();

        User user = userService.findUserByUsernameAndPassword(username, password);

        if(user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                    .body(new ResponseDto(HttpStatus.UNAUTHORIZED.value(), "인증되지 않은 사용자입니다."));

        AuthUser authUser = new AuthUser(user.getCode(), user.getUsername(), user.getNickname());
        session.setAttribute("authUser", authUser);

        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "성공적으로 로그인 되었습니다."));
    }

    @GetMapping("/signout")
    public ResponseEntity<ResponseDto> signout(HttpSession session) {
        session.removeAttribute("authUser");
        session.invalidate();

        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "로그아웃 완료"));
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

    @PutMapping({"", "/"})
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserRequestDto userDto) {
        boolean isSuccess = userService.updateUser(userDto);

        if(!isSuccess)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST.value())
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "회원정보 수정에 실패하였습니다."));

        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "회원정보가 성공적으로 수정되었습니다."));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseDto> deleteUserByUsername(@PathVariable String username) {
        boolean isSuccess = userService.deleteUserByUsername(username);
        if(!isSuccess)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST.value())
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다."));
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "회원정보가 성공적으로 삭제되었습니다."));
    }


}
