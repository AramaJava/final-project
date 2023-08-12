package ru.maxima.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maxima.finalproject.models.Person;

/**
 * @author AramaJava 10.08.2023
 */

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query
    String findNameById(Long adminId);
}
