package com.example.weatheralert.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

// 요즘은 백앤드와 프론트앤드를 완전히 나눠서 개발하기 때문에 백앤드에서 ModelAndView 를 사용할 일은 거의 없다.
// 즉, 백앤드에서는 Api개발에 그외 성능 개선이나 인프라 적인 요소에 집중하게 된다. 하지만 일부 간단한 프로젝트에서는
// 아직도 백앤드에서 풀스택으로 개발하는 경우가 많으며 그때는 ModelAndView 가 많이 사용된다.

//이 경우 Controller 에 @RestController를 지정해야 할까 @Controller 를 지정해야 할까? 정석은 api형태의 컨트롤러와
// view형태의 컨트롤러를 클래스로 분리해서 각각 @RestController 와 @Controller 를 지정하는 것인데 view형태의 컨트롤러를 사용한다는 것은
// 이미 소규모 프로젝트라는 말이고 굳이 컨트롤러를 나눠서 작성할 필요까진 없는 경우가 대부분이다.

//이때는 기본적으로 @RestController 만 지정해도 모두 처리가 가능한데 리턴타입이 ModelAndView 일 경우 view로 이동하고
// 그 외 타입일 경우 json 문자열로 직렬화를 시도한다.



@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class loginController {

    private final loginService service;

//    @GetMapping("/")
//    public ModelAndView loginFrm(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("login");
//
//        return mv;
//    }


    @GetMapping("/msg")
    public String frm(){

        return "heloo한국";
    }


    // 그저 로그인이 잘되는지 확인용.
    @PostMapping("/loginmsg")
    public String login(User user){

        String msg = "login fail";
        if(service.getUser(user.getId()).getPassword()!=null){
            msg = "login success";
        }

        return msg;
    }

}

// JSON 데이터 한글화
//    JSON Object 저장 할 때
//    HTTP response 의 Character set 을 UTF-8 로 설정하면
//    한글 깨지는 현상 막을 수 있습니다.
// server.servlet.encoding.charset=UTF-8 server.servlet.encoding.force=true 추가함