package org.example.module4.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;

    @ManyToOne
    UserEntity userEntity;

    @ManyToOne
    NewsCategoryEntity newsCategoryEntity;

    @OneToMany(mappedBy = "newsEntity")
    List<CommentEntity> commentEntities;

    public NewsEntity(String content, UserEntity userEntity, NewsCategoryEntity newsCategoryEntity) {
        this.content = content;
        this.userEntity = userEntity;
        this.newsCategoryEntity = newsCategoryEntity;
        this.commentEntities=new ArrayList<>();
    }
}
