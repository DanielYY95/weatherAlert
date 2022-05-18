package com.example.weatheralert.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface loginService {


    // 로그인 기능
    public User getUser(String userId);

    // 로그아웃
    
    // 아이디 기억

}
