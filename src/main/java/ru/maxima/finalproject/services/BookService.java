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

    // создание книги
    boolean createBook (Book book);

    // метка на удаление
    void removeBookById(Long bookId);

    // редактировать книгу (админ)
    ResponseEntity<Book> updateBook (Book book);

    // взять книгу (любой авторизовавшийся)
    ResponseEntity<Book> takeBook (Book book);

    // вернуть книгу (любой авторизовавшийся)
    ResponseEntity<Book> returnBook (Book book);


}
