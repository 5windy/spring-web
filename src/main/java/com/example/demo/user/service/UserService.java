package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRepository;
import com.example.demo.user.domain.UserRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public User findUserByCode(long code) {
//        User user = userRepository.findById(code).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 회원의 정보입니다.")
//        );
        Optional<User> user = userRepository.findById(code);

        if(user.isEmpty())
            return null;

        return user.get();
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean createUser(User user) {
        // username에 대한 중복 예외처리 후, 결과 반환
        User target = userRepository.findUserByUsername(user.getUsername());

        if(target != null)
            return false;

        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean updateUser(UserRequestDto userDto) {
        // 1. 요청객체의 정보(username, password)를 활용하여 -> entity 객체 조회
        // 2. entity 객체의 값을 수정

        String username = userDto.getUsername();
        String password = userDto.getPassword();

        User target = userRepository.findUserByUsernameAndPassword(username, password);

        if(target == null)
            return false;

        target.update(userDto);
        return true;
    }

    @Transactional
    public boolean deleteUserByUsername(String username) {
        User target = userRepository.findUserByUsername(username);

        if(target == null)
            return false;

        userRepository.delete(target);
        return true;
    }
}
