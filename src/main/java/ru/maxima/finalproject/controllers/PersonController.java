package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        boolean isPersonCreated = personService.createPerson(person);

        if (isPersonCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Person is created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create person");
        }

    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("{personId}")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public Optional<Person> getOnePerson(@PathVariable Long personId) {
        return personService.getOnePerson(personId);
    }

    @PostMapping("/block")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public ResponseEntity<String> blockPerson(@RequestBody Person person) {
        boolean isPersonBlocked = personService.blockPerson(person);

        if (isPersonBlocked) {
            return ResponseEntity.status(HttpStatus.OK).body("Person is blocked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to block person");
        }
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public ResponseEntity<String> editPerson(@RequestBody Person person) {
        boolean isPersonEdited = personService.editPerson(person);

        if (isPersonEdited) {
            return ResponseEntity.status(HttpStatus.OK).body("Person is updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
        }
    }

}
