package ru.maxima.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.model.Book;
import ru.maxima.finalproject.repository.BookRepository;

import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> allBooks() {
        return bookRepository.findAll();

    }
}
