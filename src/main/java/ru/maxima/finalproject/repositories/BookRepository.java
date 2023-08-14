package ru.maxima.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maxima.finalproject.models.Book;

import java.util.List;

/**
 * @author AramaJava 10.08.2023
 */

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM BOOK b WHERE b.removedAt is null", nativeQuery = true)
    List<Book> findAllNotRemoved();
}
