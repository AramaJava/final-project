package ru.maxima.finalproject.services;
import ru.maxima.finalproject.models.Person;
import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 19.08.2023
 */

public interface PersonService {

    // добавить персону в базу (регистрация)
    boolean createPerson(Person person);

    // получить список всех персн
    List<Person> getAllPersons();

    // редактировать персону
    boolean editPerson (Person person);

    // пометить на удаление (removedAt)
    boolean blockPerson (Person person);

    // получить имя персоны из базы
    String getPersonNameFromDB(Long id);

    // получить персону по id
    Optional<Person> getOnePerson(Long personId);



}

