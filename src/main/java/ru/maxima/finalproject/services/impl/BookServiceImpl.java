package ru.maxima.finalproject.services.impl;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.finalproject.exceptions.BookNotFoundException;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.BookRepository;
import ru.maxima.finalproject.services.BookService;
import ru.maxima.finalproject.services.PersonService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 10.08.2023
 */

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final PersonService personService;
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

    public void removeBookById(Long bookId) throws BookNotFoundException {
        String removedPersonName = jwtService.getCurrentPersonFromToken().getName();

        Book bookForRemove = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
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
    public List<Book> takeBook(Long bookId, Long personId) throws BookNotFoundException {

            var person = personService.getOnePerson(personId).orElseThrow(UserNotFoundException::new);
            var book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
            List <Book> books = person.getTakenBooks();
            if (books == null) {
                books = new ArrayList<>();
            }

            books.add(book);
            person.setTakenBooks(books);
            return person.getTakenBooks();

        }



       /* Book bookForTake = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        if (bookForTake.getOwner() == null) {
            bookForTake.setOwner(jwtService.getCurrentPersonFromToken());
            bookRepository.save(bookForTake);
            return true;
        } else {
            return false;
        }
    }*/


    @Override
    public boolean returnBook(Long bookId) throws BookNotFoundException {

        Book bookForReturn = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        if ((bookForReturn.getOwner() != null)
                && (bookForReturn.getOwner().getId().equals(jwtService.getCurrentPersonFromToken().getId()))) {
            bookForReturn.setOwner(null);

            bookRepository.save(bookForReturn);
            return true;
        } else {
            return false;
        }

    }


    @Override
    public List<Book> getTakenBooks(Long personId) throws UserNotFoundException {

        Person person = personService.getOnePerson(personId).orElseThrow(UserNotFoundException::new);
        return person.getTakenBooks();
    }

    @Override
    public Optional<Book> getBook(Long bookId) {
        return Optional.empty();
    }

 /*   @Override
    public Optional<Book> getBook(Long bookId) throws BookNotFoundException {
        return Optional.ofNullable(bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new));
    }*/
}
