package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.services.AuthService;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.services.PersonService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final PersonService personService;

    @GetMapping("/get-token")
    public String authentication(@RequestBody Person person) {
        return authService.authentication(person);
    }

    @PostMapping
    public ResponseEntity<String> loginPerson(@RequestBody Person person) {
        boolean isPersonCreated = personService.createPerson(person);
        if (isPersonCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Person created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create person");
        }
    }
}
