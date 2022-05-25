package com.example.weatheralert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;


// Spring Boot는 DispatcherServlet를 서블릿으로 자동으로 등록하면서 모든 경로에 대해서 매핑
// 즉, urlPatterns="/"에 대해 매핑


@SpringBootApplication
public class WeatherAlertApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAlertApplication.class, args);


    }


}
