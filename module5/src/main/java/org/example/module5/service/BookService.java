package org.example.module5.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.module5.adapter.web.dto.BookFullRs;
import org.example.module5.adapter.web.dto.BookRq;
import org.example.module5.adapter.web.dto.BookRs;
import org.example.module5.adapter.web.dto.SearchRq;
import org.example.module5.adapter.web.repository.BookRepository;
import org.example.module5.adapter.web.repository.CategoryRepository;
import org.example.module5.domain.Book;
import org.example.module5.domain.Category;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper mapper;

    @Cacheable(value = "books", key = "#searchRq.name + #searchRq.author")
    public BookFullRs findByNameAndAuthor(SearchRq searchRq) {
        return mapper.bookToFullRs(
                bookRepository
                        .findByNameAndAuthor(searchRq.bookName(), searchRq.author())
                        .orElseThrow());
    }

    @CacheEvict(value = {"books", "booksByCategory"}, allEntries = true)
    public BookFullRs create(BookRq bookRq) {
        Category category = categoryRepository.findById(bookRq.categoryId()).orElseThrow();
        Book book = new Book(bookRq.bookName(), bookRq.author(), category);
        bookRepository.save(book);
        return mapper.bookToFullRs(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, key = "#id", beforeInvocation = true)
    public void updateById(Long id, Book book) {
        Book repoBook = bookRepository.findById(id).orElseThrow();
        book.setId(repoBook.getId());
        bookRepository.save(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, key = "#id", beforeInvocation = true)
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        bookRepository.deleteById(id);
    }

    @Cacheable(value = "booksByCategory", key = "#category")
    public List<BookRs> findAllByCategory(String category) {
        return categoryRepository.findAll()
                .stream().filter(it -> it.getName().equals(category))
                .flatMap(e -> e.getBooks().stream()).map(mapper::bookToRs)
                .toList();
    }
}
