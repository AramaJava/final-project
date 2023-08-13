package ru.maxima.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.maxima.finalproject.interfaces.AuthService;
import ru.maxima.finalproject.models.Person;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reg/{adminId}")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN)")
    public void registration(@RequestBody Person user, @PathVariable Long adminId) {
        authService.registration(user, adminId);
    }

    @GetMapping("/login")
    public String authentication(@RequestBody Person person) {
        return authService.authentication(person);
    }
}
