package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.exceptions.BookNotFoundException;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.services.BookService;


import java.util.List;


/**
 * @author AramaJava 10.08.2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() throws BookNotFoundException {
        List<Book> allBooks = bookService.allBooks();
        if (allBooks == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allBooks.isEmpty())
            return new ResponseEntity<>(allBooks, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping()
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        boolean isBookCreated = bookService.createBook(book);

        if (isBookCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create Book");
        }

    }

    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping("/remove/{bookId}")
    public void removeBookById(@PathVariable Long bookId) throws BookNotFoundException {
        bookService.removeBookById(bookId);
    }

    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping("/edit")
    public ResponseEntity<String> editBook(@RequestBody Book book) throws BookNotFoundException {
        boolean isBookEdited = bookService.editBook(book);
        if (isBookEdited) {
            return ResponseEntity.status(HttpStatus.OK).body("Book is updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookNotFoundException().getMessage());
        }
    }

    @GetMapping ("/takeBook/{bookId}/{personId}")
    public List<Book> takeBook(@PathVariable Long bookId,
                               @PathVariable Long personId) throws BookNotFoundException {
        return bookService.takeBook(bookId, personId);

    }



/*
    @PostMapping("/take/{personId}/{bookId}")
    public ResponseEntity<Book> takeBook(@PathVariable Long bookId, @PathVariable Long personId) {
        boolean isTakenBook;
        try {
            isTakenBook = bookService.takeBook(bookId, personId);
            if (isTakenBook) {
                return ResponseEntity.status(HttpStatus.OK).body("Book is taken successfully");
            } else {
                return ResponseEntity.status(HttpStatus.LOCKED).body("Book is locked");
            }

        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }
*/

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        boolean isReturnedBook;
        try {
            isReturnedBook = bookService.returnBook(bookId);
            if (isReturnedBook) {
                return ResponseEntity.status(HttpStatus.OK).body("Book is returned successfully");
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You cant return this book!");

        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }



    @GetMapping("/getbooks/{personId}")
    public List<Book> getTakenBooks (@PathVariable Long personId) throws BookNotFoundException, UserNotFoundException {

        return bookService.getTakenBooks(personId);


    }

/*
    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable Long bookId) throws BookNotFoundException {
        return ResponseEntity.ok().body(bookService.getBook(bookId));
    }
*/



}


