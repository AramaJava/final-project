
package ru.maxima.finalproject.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.PersonRepository;

@Service
@RequiredArgsConstructor
public class JWTService {
    private final PersonRepository personRepository;

    private static final String SECRET = "231123333";

    public String getToken(Person person) {
        Person personFromDB = personRepository
                .findByEmail(person.getEmail())
                .orElseThrow(UserNotFoundException::new);

        return JWT.create()
                .withClaim("Email", personFromDB.getEmail())
                .withClaim("Role", personFromDB.getRole())
                .sign(Algorithm.HMAC256(SECRET));
    }
}
