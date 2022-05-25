package com.example.weatheralert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/register", "/swagger-ui.html").permitAll() // swagger 흰 화면만..
                            // 로그인없이도 누구나 접근 가능 // css 같은 것들도 설정필요
                    .anyRequest().authenticated() // 여기를 제외한 그밖의 요청들은 인증받아야한다.
                    .and() // 이어서
                .formLogin()
                    .loginPage("/login") // 로그인 페이지 설정
                    .permitAll() // 로그인 누구나 할 수 있게
                    .and()
                .logout()
                    .permitAll();
    }

    // 로그인을 위해서는 SecurityConfig 클래스에 AuthenticationManagerBuilder를 주입해서 인증에 대한 처리를 해야 합니다.
    // AuthenticationManagerBuilder는 인증에 대한 다양한 설정을 생성할 수 있습니다.
    // 예를 들어, 메모리상 정보를 이용하거나, JDBC, LDAP등의 정보를 이용해서 인증 처리가 가능합니다.
    // AuthenticationMangerBuilder 인스턴스를 가지고 스프링이 알아서 처리해준다.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource) // datasource를 가지고 인증
                .passwordEncoder(passwordEncoder()) // 비밀번호 암호화
                .usersByUsernameQuery("select id,password,enabled " // 사용자 정보를 가져오는 쿼리
                        + "from userinfo " // 이렇게 공백을 만들어줘야 붙지 않는다.
                        + "where id = ?")
                .authoritiesByUsernameQuery("select id, code " // 권한 정보를 가져오는 쿼리
                        + "from userrole ur inner join userinfo u on ur.user_id = u.id "
                        + "inner join role r on ur.role_id = r.id"
                        + "where u.id = ?");
    }

    // Authentication 로그인 처리(인증)
    // Authroization 권한 처리(인가)

    // 비밀번호 encoder
        // 안전하게 암호화해준다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}