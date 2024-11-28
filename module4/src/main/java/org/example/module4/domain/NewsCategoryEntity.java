package org.example.module4.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "news_category")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class NewsCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String categoryName;
    @OneToMany(mappedBy = "newsCategoryEntity")
    List<NewsEntity> newsEntityList;

    public NewsCategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }
}
