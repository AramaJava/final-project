package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.services.BookService;


import java.security.Principal;
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
    public List<Book> getAllBooks() {
        return bookService.allBooks();
    }


    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping("/add-book")
    public void addBook(@RequestBody Book book, Principal principal) {
      bookService.newBook(book, principal);
    }

    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping("/remove/{bookId}")
    public void removeBookById(@PathVariable Long bookId, Principal principal) {

        bookService.removeBookById(bookId, principal);
    }
}
