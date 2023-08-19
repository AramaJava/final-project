package ru.maxima.finalproject.services.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.configurations.Authorities;
import ru.maxima.finalproject.exceptions.UserNotFoundException;

import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.PersonRepository;
import ru.maxima.finalproject.services.PersonService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 12.08.2023
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final JwtServiceImpl jwtService;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String getPersonNameFromDB(Long id) {
        return personRepository.findPersonById(id).orElseThrow(UserNotFoundException::new).getName();
    }
    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }
    @Override
    public Optional<Person> findOnePerson(Long personId) {
        Optional<Person> p = personRepository.findPersonById(personId);

        if (p.isPresent()) {
            return p;
        } else throw new UserNotFoundException();
    }

    @Override
    public boolean createPerson(Person person) {
        // check id person already exist
        if (personRepository.existsByEmail(person.getEmail())) {
            return false;
        }
        Person personForSave = Person.builder()
                .name(person.getName())
                .createdPerson(jwtService.getCurrentPersonFromToken().getName())
                .password(passwordEncoder.encode(person.getPassword()))
                .email(person.getEmail())
                .role(Authorities.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .build();
        personRepository.save(personForSave);

        return true;
    }
}
