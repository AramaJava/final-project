package ru.maxima.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxima.finalproject.model.Book;
import ru.maxima.finalproject.service.BookService;

import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */
@RestController
@RequiredArgsConstructor
public class BookController {

   private final BookService bookService;

   public List<Book> getAllBooks() {
       return bookService.allBooks();
   }
}
