package com.example.demo.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // public User 나만의쿼리크리션메소드();

    // select * from users where username=?1
    public User findUserByUsername(String username);

    public User findUserByUsernameAndPassword(String username, String password);

}
