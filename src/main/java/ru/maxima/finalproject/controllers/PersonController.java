package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.services.PersonService;

import java.util.List;

/**
 * @author AramaJava 12.08.2023
 */

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    @GetMapping("/persons")
    public List<Person> getAllPerson() {
        return personService.allPersons();
    }
}