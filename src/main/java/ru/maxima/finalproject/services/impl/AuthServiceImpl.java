package ru.maxima.finalproject.services.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.finalproject.models.Person;


import ru.maxima.finalproject.services.AuthService;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String authentication(Person person) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(person.getEmail(),person.getPassword());

            authenticationManager.authenticate(authenticationToken);
            return jwtService.getToken(person);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
