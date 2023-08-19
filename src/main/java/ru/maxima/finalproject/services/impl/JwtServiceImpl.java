
package ru.maxima.finalproject.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.PersonRepository;
import ru.maxima.finalproject.services.JwtService;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    public static final String SECRET = "231123333";
    private final PersonRepository personRepository;

    @Override
    public String getToken(Person person) {
        Person personFromDB = personRepository
                .findByEmail(person.getEmail())
                .orElseThrow(UserNotFoundException::new);

        return JWT.create()
                .withClaim("Email", personFromDB.getEmail())
                .withClaim("Role", personFromDB.getRole())
                .withClaim("Name", personFromDB.getName())

                .sign(Algorithm.HMAC256(SECRET));
    }
    @Override
    public Person getCurrentPersonFromToken() {
        return ((Person) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

}
