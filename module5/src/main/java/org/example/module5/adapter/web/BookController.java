package org.example.module5.adapter.web;

import lombok.RequiredArgsConstructor;
import org.example.module5.adapter.web.dto.BookFullRs;
import org.example.module5.adapter.web.dto.BookRq;
import org.example.module5.adapter.web.dto.BookRs;
import org.example.module5.adapter.web.dto.SearchRq;
import org.example.module5.domain.Book;
import org.example.module5.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public BookFullRs getByNameAndAuthor(@RequestBody SearchRq searchRequest) {
        return bookService.findByNameAndAuthor(searchRequest);
    }

    @GetMapping("/{category}")
    public List<BookRs> getAllByCategory(@PathVariable("category") String category) {
        return bookService.findAllByCategory(category);
    }

    @PostMapping
    public BookFullRs create(@RequestBody BookRq book) {
        return bookService.create(book);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Book book) {
        bookService.updateById(id, book);
    }
}
