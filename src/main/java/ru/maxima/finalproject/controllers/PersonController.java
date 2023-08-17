package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.services.AuthService;
import ru.maxima.finalproject.services.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 12.08.2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;
    private final AuthService authService;

    @PostMapping("/reg/{adminId}")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public void registration(@RequestBody Person user, @PathVariable Long adminId) {
        authService.registration(user, adminId);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("{personId}")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public Optional<Person> getOnePerson(@PathVariable Long personId) {
        return personService.findPerson(personId);
    }
}
