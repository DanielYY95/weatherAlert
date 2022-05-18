package com.example.weatheralert.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class normalController {

    @GetMapping("/login/")
    public String loginfrm(){


        return "login.html";
    }

}
