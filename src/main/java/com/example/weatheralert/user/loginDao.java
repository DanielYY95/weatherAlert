package com.example.weatheralert.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface loginDao extends JpaRepository {


    // 참고: https://devkingdom.tistory.com/110
        // https://blog.neonkid.xyz/219
   // public static List<User> users;

//    //아직 DB가 없으니 임시로 세팅해놓음
//    static {
//        users = new ArrayList<>();
//        users.add(new User("testID1","testPW1", "aaa@naver.com", "on", "1234"));
//        users.add(new User("testID12","testPW2", "bbb@naver.com", "off", "1234"));
//        users.add(new User("testID13","testPW3", "ccc@naver.com", "on", "1234"));
//        users.add(new User("testID14","testPW4", "ddd@naver.com", "off", "1234"));
//        users.add(new User("testID15","testPW5", "eee@naver.com", "on", "1234"));
//    }

//    // Select all user.
//    public List<User> getAllUsers(){
//
//
//    };
//
//    public User getUser(String userId){
//
//
//    };

}
