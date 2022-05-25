package com.example.weatheralert.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor // 이것과 interface Service 같이
public class normalController {

    private final loginService service;


    @GetMapping("/")
    public String home(){

        return "index";
    }


    @GetMapping("/register")
    public String regFrm(){

        return "register";
    }
    @PostMapping("/register")
    public String reg(User user){
        service.save(user); //회원가입
 

        return "redirect:/app";
    }


    @GetMapping("/login")
    public String loginfrm(){


        return "login";
    }

    @GetMapping("/app")
    public String settingFrm(@RequestParam(name="name", required = false) String name,
                             @RequestParam(name="code", required = false) Integer code, Model m){

        m.addAttribute("name", name);
        m.addAttribute("code", code);

        return "setting";
    }

}




//[먹보 네오] [오후 3:02] static 폴더가 아니였던것 같아요
//[먹보 네오] [오후 3:02] 정적인 리소스를 static에 넣구 사용했던것 같은데
//[먹보 네오] [오후 3:02] 폴더 구조가 프로젝트마다 달라서 기억이 잘 안나네요 ㅠㅠ
//[김뉴비] [오후 3:02] resources내부에
//[먹보 네오] [오후 3:02] WEB-INF?
//[김뉴비] [오후 3:02] template
//[초롱초롱 튜브] [오후 3:02] jsp나 리액트, vue 같이 서버 단에서 동적인 처리가 필요한 것은 templates라고 기억하는데
//[김뉴비] [오후 3:02] 폴더만들고 거기를
//[김뉴비] [오후 3:02] 기본경로로잡으심됩니당
//[Exception] [오후 3:02] jar vs war
//[먹보 네오] [오후 3:03] template 나 WEB-INF 이런거에 넣었던것 같아요
//[김뉴비] [오후 3:03] 스프링내부구조도 다 뜯어보고싶은데
//[김뉴비] [오후 3:03] 어우