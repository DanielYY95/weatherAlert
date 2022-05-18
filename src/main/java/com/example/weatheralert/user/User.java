package com.example.weatheralert.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String alert;

    @Column(nullable = false, length = 100)
    private String home;

    public User(String id, String password, String email, String alert, String home) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.alert = alert;
        this.home = home;
    }
}
