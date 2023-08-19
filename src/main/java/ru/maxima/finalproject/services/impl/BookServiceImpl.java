package ru.maxima.finalproject.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.repositories.BookRepository;
import ru.maxima.finalproject.services.BookService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final JwtServiceImpl jwtService;


    public List<Book> allBooks() {
        return bookRepository.findByRemovedAtIsNull();
    }

    public ResponseEntity<Book> saveBook (Book book) {

        Book bookForSave = Book.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .yearOfProduction(book.getYearOfProduction())
                .annotation(book.getAnnotation())
                .createdPerson(jwtService.getCurrentPersonFromToken().getName())
                .createdAt(LocalDateTime.now())
                .build();
        bookRepository.save(bookForSave);

        return ResponseEntity.created(null).build();
    }

    public void removeBookById(Long bookId) {

        Book bookForRemove = bookRepository.findBookById(bookId);
        bookForRemove.setRemovedAt(LocalDateTime.now());
        bookForRemove.setRemovedPerson(jwtService.getCurrentPersonFromToken().getName());
        bookForRemove.setUpdatedAt(LocalDateTime.now());
        bookForRemove.setUpdatedPerson(jwtService.getCurrentPersonFromToken().getName());
        bookRepository.save(bookForRemove);
    }

}
