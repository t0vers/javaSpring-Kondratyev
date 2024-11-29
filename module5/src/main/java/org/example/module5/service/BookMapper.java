package org.example.module5.service;

import org.example.module5.adapter.web.dto.BookFullRs;
import org.example.module5.adapter.web.dto.BookRs;
import org.example.module5.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookFullRs bookToFullRs(Book book){
        return new BookFullRs(book.getName(), book.getCategory().getName(), book.getAuthor());
    }

    public BookRs bookToRs(Book book){
        return new BookRs(book.getName(), book.getAuthor());
    }
}
