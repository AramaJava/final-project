package ru.maxima.finalproject.services;

import org.springframework.http.ResponseEntity;
import ru.maxima.finalproject.models.Book;


import java.util.List;

/**
 * @author AramaJava 19.08.2023
 */

public interface BookService {

    // все книги
    List<Book> allBooks();

    // сохранение книги
    ResponseEntity<Book> saveBook (Book book);

    // метка на удаление
    void removeBookById(Long bookId);
}
