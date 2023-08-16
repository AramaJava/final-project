package ru.maxima.finalproject.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 12.08.2023
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public String getPersonName(Long id) {

        Optional<Person> p = personRepository.findPersonById(id);

        if (p.isPresent()) {
            return p.get().getName();
        } else throw new UserNotFoundException();

    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

}
