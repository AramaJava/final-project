package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    public List<Book> getAllBooks() {
        return bookService.allBooks();
    }


    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping("/addbook/{adminId}")
    public void addBook(@RequestBody Book book, @PathVariable Long adminId) {
        bookService.newBook(book, adminId);
    }

    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping("/remove/{bookId}")
    public void removeBookById(@PathVariable Long bookId, Long adminId) {
        bookService.removeBookById(bookId, adminId);
    }
}
