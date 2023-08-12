package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxima.finalproject.models.Book;
import ru.maxima.finalproject.dto.HelloBean;
import ru.maxima.finalproject.services.BookService;

import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */

@RestController
@RequiredArgsConstructor
public class BookController {

   private final BookService bookService;
    @GetMapping("/books")
    public List<Book> getAllBooks() {
       return bookService.allBooks();
   }

    @GetMapping("/hello")
    public HelloBean helloBean() {
      return new HelloBean("Hello World");
   }


}
