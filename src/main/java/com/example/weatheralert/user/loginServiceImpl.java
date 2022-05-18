package com.example.weatheralert.user;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class loginServiceImpl implements loginService{

    // 생성자 주입 vs @Autowired
    // 참고:https://madplay.github.io/post/why-constructor-injection-is-better-than-field-injection

   // private final loginDao dao;

    public static List<User> users;

    //아직 DB가 없으니 임시로 세팅해놓음
    static {
        users = new ArrayList<>();
        users.add(new User("testID1","testPW1", "aaa@naver.com", "on", "1234"));
        users.add(new User("testID12","testPW2", "bbb@naver.com", "off", "1234"));
        users.add(new User("testID13","testPW3", "ccc@naver.com", "on", "1234"));
        users.add(new User("testID14","testPW4", "ddd@naver.com", "off", "1234"));
        users.add(new User("testID15","testPW5", "eee@naver.com", "on", "1234"));
    }


    public User getUser(String userId){

        return users.stream().filter(user-> user.getId().equals(userId)).findAny().orElse(new User());
    };


}
