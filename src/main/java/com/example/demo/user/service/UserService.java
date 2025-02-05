package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRepository;
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

    public boolean createUser(User user) {
        // username에 대한 중복 예외처리 후, 결과 반환
        User target = userRepository.findUserByUsername(user.getUsername());

        if(target != null)
            return false;

        userRepository.save(user);
        return true;
    }

    public boolean deleteUserByUsername(String username) {
        User target = userRepository.findUserByUsername(username);

        if(target == null)
            return false;

        userRepository.delete(target);
        return true;
    }
}
