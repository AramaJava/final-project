package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.services.impl.PersonServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 12.08.2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonServiceImpl personService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public List<Person> getAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping("{personId}")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public Optional<Person> getOnePerson(@PathVariable Long personId) {
        return personService.findOnePerson(personId);
    }


}
