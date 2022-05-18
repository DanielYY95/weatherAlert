package com.example.weatheralert.config;


//Swagger 설정을 정의한 코드입니다.
//        .consume()과 .produces()는 각각 Request Content-Type, Response Content-Type에 대한 설정입니다.(선택)
//        .apiInfo()는 Swagger API 문서에 대한 설명을 표기하는 메소드입니다. (선택)
//        .apis()는 Swagger API 문서로 만들기 원하는 basePackage 경로입니다. (필수)
//        .path()는 URL 경로를 지정하여 해당 URL에 해당하는 요청만 Swagger API 문서로 만듭니다.(필수)

// 에러: https://velog.io/@dldydrhkd/Failed-to-start-bean-documentationPluginsBootstrapper-nested-exception-is-java.lang.NullPointerException
// 에러: org.springframework.context.ApplicationContextException: Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException
    // 해결 => webMvcConfigurationSupport를 상속받는다.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:5080/swagger-ui.html

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {
    //swagger 2.9.2 버전 리소스 등록
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //spring-security와 연결할 때 이 부분을 작성하지 않으면 404에러가 뜬다.
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any()) // base-package
                .paths(PathSelectors.any()) // URL 주소
                .build();
    }

    // api의 정보들
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("daniel API")
                .version("1.0.0")
                .description("Daniel의 swagger api 입니다.")
                .build();
    }



}