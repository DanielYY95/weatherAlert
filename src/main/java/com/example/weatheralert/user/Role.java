package com.example.weatheralert.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "roles") // 상대 테이블의 컬럼(조인됬을 때 생기는)
    private List<User> users;// STOPSHIP: 2022-05-25


}
