package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.configurations.details.PersonDetailsService;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.services.AuthService;
import ru.maxima.finalproject.models.Person;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final PersonDetailsService personDetailsService;

    @GetMapping("/get-token")
    public String authentication(@RequestBody Person person) {
        return authService.authentication(person);
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPerson(@RequestBody Person person) throws UserNotFoundException {
        try {
            UserDetails personDetails = personDetailsService.loadUserByUsername(person.getEmail());
            if (!personDetails.isEnabled()) return ResponseEntity.status(HttpStatus.LOCKED).body("Person is blocked!");
            if (passwordEncoder.matches(person.getPassword(), personDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body("Person logged successfully \n Token: " +  authService.authentication(person));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person failed to login");
        } catch (UserNotFoundException ue) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ue.getMessage());
        }
    }
}