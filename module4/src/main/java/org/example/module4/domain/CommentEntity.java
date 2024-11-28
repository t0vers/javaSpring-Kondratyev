package org.example.module4.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;

    @ManyToOne
    UserEntity userEntity;
    @ManyToOne
    NewsEntity newsEntity;

    public CommentEntity(String content,UserEntity user, NewsEntity news) {
        this.content = content;
        this.userEntity = user;
        this.newsEntity = news;
    }
}
