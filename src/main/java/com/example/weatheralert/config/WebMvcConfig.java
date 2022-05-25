package com.example.weatheralert.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
    
    // 시큐리티
    // 음... 이게 뭐에 쓰는거지...
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
    }


//    private static final String VIEW_PREFIX = "/";//
//
//    private static final String VIEW_CONTENT_TYPE = "text/html;charset=UTF-8";//
//
//    @Bean
//    public ViewResolver viewResolver(){
//
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setCache(true);
//        resolver.setPrefix(VIEW_PREFIX);
//
//        resolver.setExposeContextBeansAsAttributes(true);
//        resolver.setContentType(VIEW_CONTENT_TYPE);
//        return resolver;
//    }


    @Bean
    public HttpMessageConverter responseBodyConverter(){
        //
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
        
    }



}

// json http response utf-8 설정
// 참고: https://yoojh9.github.io/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-HttpMessageConverter/
// 일단 범인은 StringHttpMessageConverter 인듯
// HttpMessageConverter란 무엇일까.
//HttpMessageConverter란 인터페이스이며, 구현한 구현체는 ByteArrayHttpMessageConverter, ResourceHttpMessageConverter, StringHttpMessageConverter가 있다.
//HttpMEssageConverter는 HTTP요청 및 응답의 body를 InputStream과 outputStream을 통해 읽고 쓰는 역할을 한다.
//즉, StringHttpMessageConverter는 HTTP의 응답을 String(문자열)로 변환해주는 역할을 한다.
//
//
//** 확인해본 결과, restTemplate이 기본적으로 갖고 있는 messageConverter는 6가지가 있는데, 기존에 갖고 있던 StringHttpMessageConverter의 default Charset이 ISO-8859-1로 되어있었다. 아마 초기에 UTF-8로 셋팅할 수 있는 방법이 있다면 위의 방법은 건너 뛸 수 있을 것 같다.
//
//출처: https://cornswrold.tistory.com/402 [평범한개발자노트]

//API 측(서버)에서 스프링 부트 2.2.0 이상을 사용하면 요청헤더에
//Content-Type: applicatoin/json; charset=UTF-8 으로 요청해서
//charset=UTF-8 헤더가 소거되어 응답하기 때문에
//클라이언트(사용자측)에서 Content-Type 에 있는 charset 을 선택하면서
//String response = restTemplate.getObject("{requestUrl}", String.class);
//으로 받게되는 경우 response 가 ISO_8859_1 캐릭터셋을 사용하여
//한글이 깨져보일 수 있다는 것을 주의하면 됩니다.
// 참고: http://honeymon.io/tech/2019/10/23/spring-deprecated-media-type.html