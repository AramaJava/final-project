package ru.maxima.finalproject.services;

import ru.maxima.finalproject.exceptions.BookNotFoundException;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.models.Book;


import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 19.08.2023
 */

public interface BookService {

    // все книги
    List<Book> allBooks() throws BookNotFoundException;

    // создание книги
    boolean createBook (Book book);

    // метка на удаление
    void removeBookById(Long bookId) throws BookNotFoundException;

    // редактировать книгу (админ)
    boolean editBook(Book book) throws BookNotFoundException;

    // взять книгу (любой авторизовавшийся)
    List<Book> takeBook (Long bookId, Long personId) throws BookNotFoundException;

    // вернуть книгу (любой авторизовавшийся)
    boolean returnBook (Long bookId) throws BookNotFoundException;

    List<Book> getTakenBooks (Long personId) throws UserNotFoundException, BookNotFoundException;

    Optional<Book> getBook(Long bookId) throws BookNotFoundException;
}
