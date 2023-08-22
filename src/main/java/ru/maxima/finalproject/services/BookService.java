package ru.maxima.finalproject.services;

import ru.maxima.finalproject.exceptions.BookNotFoundException;
import ru.maxima.finalproject.models.Book;
import java.util.List;


/**
 * @author AramaJava 19.08.2023
 */

public interface BookService {

    // все книги
    List<Book> getAllBooks();

    // создание книги
    boolean createBook (Book book);

    // метка на удаление
    void removeBookById(Long bookId) throws BookNotFoundException;

    // редактировать книгу (админ)
    boolean editBook(Book book) throws BookNotFoundException;

    // взять книгу (любой авторизовавшийся)
   boolean takeBook (Long bookId) throws BookNotFoundException;


    // вернуть книгу (любой авторизовавшийся)
    boolean returnBook (Long bookId) throws BookNotFoundException;

}
