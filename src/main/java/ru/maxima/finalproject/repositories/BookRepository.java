package ru.maxima.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.finalproject.models.Book;

import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByRemovedAtIsNull();

    boolean existsByAuthorAndName(String author, String name);


}
