package ru.maxima.finalproject.services;


import lombok.RequiredArgsConstructor;


import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.finalproject.configurations.details.PersonDetails;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.repositories.BookRepository;


import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author AramaJava 10.08.2023
 */

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final PersonService personService;

    public List<Book> allBooks() {
        return bookRepository.findByRemovedAtIsNull();

    }

    public void newBook(Book book, Principal principal) {
        Book bookForSave = Book.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .yearOfProduction(book.getYearOfProduction())
                .annotation(book.getAnnotation())
                .createdPerson(principal.getName())
                .createdAt(LocalDateTime.now())
                .build();
        bookRepository.save(bookForSave);
    }

    // показать все книги +

    // добавить книгу (админ) +

    // удалить книгу (админ)

    public void removeBookById(Long bookId, Principal principal) {


        Book bookForRemove = bookRepository.findBookById(bookId);

        bookForRemove.setRemovedAt(LocalDateTime.now());
        bookForRemove.setRemovedPerson(principal.getName());
        bookForRemove.setUpdatedAt(LocalDateTime.now());
        bookForRemove.setUpdatedPerson(principal.getName());
        bookRepository.save(bookForRemove);


    }

    // редактировать книгу (админ)

    // взять книгу

    // вернуть книгу

}
