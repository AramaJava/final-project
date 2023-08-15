package ru.maxima.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.finalproject.models.Person;

import java.util.Optional;

/**
 * @author AramaJava 10.08.2023
 */

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);


    Person findPersonById(Long adminId);


}
