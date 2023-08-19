package ru.maxima.finalproject.services;
import ru.maxima.finalproject.models.Person;
import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 19.08.2023
 */

public interface PersonService {

    // добавить персону в базу (регистрация)
    void addPerson(Person person);

    // получить список всех персн
    List<Person> findAllPersons();

    // редактировать персону

    // пометить на удаление (removedAt)


    // получить имя персоны из базы
    String getPersonNameFromDB(Long id);

    // получить персону по id
    Optional<Person> findOnePerson(Long personId);

    boolean createPerson(Person person);

}

