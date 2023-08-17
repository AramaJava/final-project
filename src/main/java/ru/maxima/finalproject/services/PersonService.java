package ru.maxima.finalproject.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.configurations.Authorities;
import ru.maxima.finalproject.exceptions.UserNotFoundException;

import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.PersonRepository;

import java.time.LocalDateTime;
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
    private final PasswordEncoder passwordEncoder;

    public Person getCurrentPerson() {
        return ((Person) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

    public String getPersonName(Long id) {

        Optional<Person> p = personRepository.findPersonById(id);

        if (p.isPresent()) {
            return p.get().getName();
        } else throw new UserNotFoundException();

    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findPerson(Long personId) {
        Optional<Person> p = personRepository.findPersonById(personId);

        if (p.isPresent()) {
            return p;
        } else throw new UserNotFoundException();
    }

    public void addPerson(Person person) {

        Person personForSave = Person.builder()
                .name(person.getName())
                .createdPerson(getCurrentPerson().getName())
                .password(passwordEncoder.encode(person.getPassword()))
                .email(person.getEmail())
                .role(Authorities.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .createdPerson(getCurrentPerson().getName())
                .build();
        personRepository.save(personForSave);

    }
}
