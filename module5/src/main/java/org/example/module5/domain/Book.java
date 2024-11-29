package org.example.module5.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "book")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String author;
    @ManyToOne
    Category category;

    public Book(String name, String author, Category category) {
        this.name = name;
        this.author = author;
        this.category = category;
    }
}
