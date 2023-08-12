package ru.maxima.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.model.Person;
import ru.maxima.finalproject.repository.PersonRepository;

import java.util.List;

/**
 * @author AramaJava 12.08.2023
 */
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> allPersons() {
        return personRepository.findAll();
    }

}
