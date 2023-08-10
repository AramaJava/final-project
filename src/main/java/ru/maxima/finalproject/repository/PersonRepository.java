package ru.maxima.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.finalproject.model.Person;

/**
 * @author AramaJava 10.08.2023
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
