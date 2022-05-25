package com.example.weatheralert.user;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class loginService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 생성자 주입 vs @Autowired
    // 참고:https://madplay.github.io/post/why-constructor-injection-is-better-than-field-injection

   // private final loginDao dao;




//    //아직 DB가 없으니 임시로 세팅해놓음
   public static List<User> users;
//    static {
//        users = new ArrayList<>();
//        users.add(new User("test1","testpw1", "aaa@naver.com", "on", 1234));
//        users.add(new User("test2","testpw2", "bbb@naver.com", "off", 1234));
//        users.add(new User("test3","testpw3", "ccc@naver.com", "on", 1234));
//        users.add(new User("test4","testpw4", "ddd@naver.com", "off", 1234));
//        users.add(new User("test5","testpw5", "eee@naver.com", "on", 1234));
//    }


    public User getUser(String userId){

        return users.stream().filter(user-> user.getId().equals(userId)).findAny().orElse(new User());
    };

     // 회원가입
    public User save(User user){

        String encodedPassword = passwordEncoder.encode(user.getPassword()); // 비밀번호 암호화한 상태로 가입
        user.setPassword(encodedPassword);
        user.setEnabled("true"); // 성공적으로 비밀번호 암호화가 됬으면 진행
        user.setAlert("on"); // 처음에는 on으로 시작
        user.setCode(0);
        
        Role role = new Role();
        role.setId("1");

        user.getRoles().add(role); // 아이디가 1번인 role 객체를 user roles에 넣는다. (권한을 준다)


        return userRepository.save(user); // 이렇게 저장하면 user_role 테이블 쪽에도 저장된다.

    };


}
