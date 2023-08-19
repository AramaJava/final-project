package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.configurations.details.PersonDetailsService;
import ru.maxima.finalproject.services.AuthService;
import ru.maxima.finalproject.models.Person;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final PersonDetailsService personDetailsService;


    @GetMapping("/get-token")
    public String authentication(@RequestBody Person person) {
        System.out.println(person.getName());
        return authService.authentication(person);
    }


    @GetMapping("/login")
    public ResponseEntity<String> loginPerson(@RequestBody Person person) {
        UserDetails personDetails = personDetailsService.loadUserByUsername(person.getEmail());
        if (personDetails.isEnabled()) {
            return ResponseEntity.status(HttpStatus.OK).body("Person logged successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person failed to login");
        }
    }
}
