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

    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping("/edit")
    public ResponseEntity<String> editBook(@RequestBody Book book) {
        boolean isBookEdited = bookService.editBook(book);
        if (isBookEdited) {
            return ResponseEntity.status(HttpStatus.OK).body("Book is updated successfully");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }

    @PostMapping("/take/{bookId}")
    public ResponseEntity<String> takeBook (@PathVariable Long bookId) {

        boolean isTakedBook = bookService.takeBook(bookId);
        if (isTakedBook) {
        return ResponseEntity.status(HttpStatus.OK).body("Book is taken successfully");
    } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }

   /* @Override
    public List<BookDto> takeBook(Long personId, Long bookId) {
        var person = personRepository.findById(personId).orElseThrow(PersonWithIdNotFoundException::new);
        List<Book> books = person.getBooks();
        if(books == null) { books = new ArrayList<>();    }

        books.add(bookRepository.findById(bookId).get());
        person.setBooks(books);
        personRepository.save(person);
        return person.getBooks().stream().map(bookMapper::toDto).collect(Collectors.toList());}
*/

}


