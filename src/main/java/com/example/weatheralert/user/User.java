package com.example.weatheralert.user;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "userinfo")
@Data
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String password;

    private String email;

    private Integer code;

    private String alert;


    private Boolean enabled;


    public User(String id, String password, String email, String alert, Integer code) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.alert = alert;
        this.code = code;
    }

    // 조인을 위해서 JPA 사용
        // 이제 User 레포지토리를 통해 조회하게되면, 유저에 해당하는 Role이 알아서 조회가 되서 담기게된다.
    @ManyToMany
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "user_id"), // 현재 entity의 id
            inverseJoinColumns = @JoinColumn(name = "role_id")) // 조인될 상대 테이블의 id
    private List<Role> roles = new ArrayList<>();



}
