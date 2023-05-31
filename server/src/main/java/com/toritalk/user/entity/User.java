package com.toritalk.user.entity;

import com.toritalk.common.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class User extends BaseTimeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String name;

    @Column
    private String password;

    @Column
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Builder
    public User(Long id, String name, String password, String imageUrl, String introduction) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
    }

}
