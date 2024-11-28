package org.example.module4.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "user_account")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "userEntity")
    List<NewsEntity> newsEntityList;
    @OneToMany(mappedBy = "userEntity")
    List<CommentEntity> commentEntities;

    public UserEntity(String name) {
        this.name = name;
    }
}
