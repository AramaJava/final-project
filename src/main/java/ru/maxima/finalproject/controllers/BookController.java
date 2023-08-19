package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.services.impl.BookServiceImpl;

import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
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
    public void removeBookById(@PathVariable Long bookId) {
        bookService.removeBookById(bookId);
    }


}
