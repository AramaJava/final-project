package ru.maxima.finalproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.finalproject.interfaces.AuthService;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.PersonRepository;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PersonRepository personRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registration(Person user, Long adminId) {

        Person personForSave = Person.builder()
                .name(user.getName())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role("User")
                .createdAt(LocalDateTime.now())
                .createdPerson(personRepository.getPersonNameById(adminId))
                .build();
        personRepository.save(personForSave);
    }

    @Override
    public String authentication(Person person) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(person.getEmail(), person.getPassword());
            authenticationManager.authenticate(authenticationToken);
            return jwtService.getToken(person);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
