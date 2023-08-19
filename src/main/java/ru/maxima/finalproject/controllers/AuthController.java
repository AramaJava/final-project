package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.services.AuthService;
import ru.maxima.finalproject.models.Person;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String authentication(@RequestBody Person person) {
        return authService.authentication(person);
    }
}
