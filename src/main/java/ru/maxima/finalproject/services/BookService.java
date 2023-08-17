package ru.maxima.finalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.BookRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    // вспомогательный метод определения текущего пользователя
    public Person getCurrentPerson() {
        return ((Person) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

    public List<Book> allBooks() {

        return bookRepository.findByRemovedAtIsNull();
    }

    public ResponseEntity<Book> saveBook (Book book) {

        Book bookForSave = Book.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .yearOfProduction(book.getYearOfProduction())
                .annotation(book.getAnnotation())
                .createdPerson(getCurrentPerson().getName())
                .createdAt(LocalDateTime.now())
                .build();
        bookRepository.save(bookForSave);

        return ResponseEntity.created(null).build();
    }

    public void removeBookById(Long bookId) {

        Book bookForRemove = bookRepository.findBookById(bookId);
        bookForRemove.setRemovedAt(LocalDateTime.now());
        bookForRemove.setRemovedPerson(getCurrentPerson().getName());
        bookForRemove.setUpdatedAt(LocalDateTime.now());
        bookForRemove.setUpdatedPerson(getCurrentPerson().getName());
        bookRepository.save(bookForRemove);
    }



}
