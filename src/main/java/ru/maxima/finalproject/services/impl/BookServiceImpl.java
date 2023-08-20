package ru.maxima.finalproject.services.impl;

import lombok.RequiredArgsConstructor;


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

    public boolean createBook(Book book) {

        // Проверяем есть ли уже книга с таким автором и таким названием

        if (bookRepository.existsByAuthorAndName(book.getAuthor(), book.getName())) {
            return false;
        }
        Book bookForSave = Book.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .yearOfProduction(book.getYearOfProduction())
                .annotation(book.getAnnotation())
                .createdPerson(jwtService.getCurrentPersonFromToken().getName())
                .createdAt(LocalDateTime.now())
                .build();

        bookRepository.save(bookForSave);
        return true;

    }

    public void removeBookById(Long bookId) {
        String removedPersonName = jwtService.getCurrentPersonFromToken().getName();

        Book bookForRemove = bookRepository.findBookById(bookId);
        bookForRemove.setRemovedAt(LocalDateTime.now());
        bookForRemove.setRemovedPerson(removedPersonName);
        bookForRemove.setUpdatedAt(LocalDateTime.now());
        bookForRemove.setUpdatedPerson(removedPersonName);
        bookRepository.save(bookForRemove);
    }

    @Override
    public boolean editBook(Book book) {
        if (bookRepository.existsById(book.getId())) {

            String updatedPersonName = jwtService.getCurrentPersonFromToken().getName();

            Book bookForUpdate = bookRepository.getReferenceById(book.getId());

            bookForUpdate.setName(book.getName());
            bookForUpdate.setAuthor(book.getAuthor());
            bookForUpdate.setYearOfProduction(book.getYearOfProduction());
            bookForUpdate.setAnnotation(book.getAnnotation());

            bookForUpdate.setUpdatedPerson(updatedPersonName);
            bookForUpdate.setUpdatedAt(LocalDateTime.now());

            bookRepository.save(bookForUpdate);
            return true;
        }
        return false;
    }


    @Override
    public boolean takeBook(Long bookId) {
        if (bookRepository.existsById(bookId)) {

            Book bookForTake = bookRepository.findBookById(bookId);
            bookForTake.setOwner(jwtService.getCurrentPersonFromToken());
            bookRepository.save(bookForTake);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(Book book) {
        return true;
    }

}
