package ru.maxima.finalproject.services;
import ru.maxima.finalproject.models.Person;
import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 19.08.2023
 */

public interface PersonService {

    // получить имя персоны из базы
    String getPersonNameFromDB(Long id);

    // получить список всех персн
    List<Person> findAllPersons();

    // получить персону по id
    Optional<Person> findOnePerson(Long personId);

    // добавить персону в базу
    void addPerson(Person person);
}

