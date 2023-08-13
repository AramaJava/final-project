package ru.maxima.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maxima.finalproject.models.Person;

import java.util.Optional;

/**
 * @author AramaJava 10.08.2023
 */

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query ("select p.name from #{#entityName} p where p.id = ?1")
    String getPersonNameById(Long adminId);

    @Query
    Optional<Person> findByEmail(String email);
}
