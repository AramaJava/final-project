package ru.maxima.finalproject.services;

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
    boolean editBook(Book book);

    // взять книгу (любой авторизовавшийся)
    boolean takeBook (Long bookId);

    // вернуть книгу (любой авторизовавшийся)
    boolean returnBook (Long bookId);



}
