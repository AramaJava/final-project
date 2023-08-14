package ru.maxima.finalproject.services;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.repositories.BookRepository;
import ru.maxima.finalproject.repositories.PersonRepository;

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

    private final PersonRepository personRepository;

    public List<Book> allBooks() {
        return bookRepository.findAllNotRemoved();

    }

    public void newBook(Book book, Long adminId) {
        Book bookForSave = Book.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .yearOfProduction(book.getYearOfProduction())
                .annotation(book.getAnnotation())
                .createdPerson(personRepository.findBy(adminId))
                .createdAt(LocalDateTime.now())
                .build();
        bookRepository.save(bookForSave);
    }

    // показать все книги +

    // добавить книгу (админ) +

    // удалить книгу (админ)

    // редактировать книгу (админ)

    // взять книгу

    // вернуть книгу

}
