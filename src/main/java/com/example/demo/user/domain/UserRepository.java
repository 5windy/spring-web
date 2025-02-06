package com.example.demo.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    // public User 나만의쿼리크리션메소드();

    // select * from users where username=?1
    public User findUserByUsername(String username);

    public User findUserByUsernameAndPassword(String username, String password);

    public User findUserByNickname(String nickname);

    // select code from users where username=?1
    // 1) query creation 으로 생성 가능한지 여부 판단,
    // 2) 불가하다면 @Query 로 nativeQuery 작성해보기

    @Query(value = "SELECT code FROM users WHERE username=?1", nativeQuery = true)
    public Long findUserCodeByUsername(String username);

//    @Query(value = "SELECT code FROM users WHERE username= :username", nativeQuery = true)
//    public Long findUserCodeByUsername(String username);

//    @NativeQuery("SELECT code FROM users WHERE username=?1")
//    public Long findUserCodeByUsername(@Param("username") String username);

}
