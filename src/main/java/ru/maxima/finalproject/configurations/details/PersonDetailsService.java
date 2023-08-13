package ru.maxima.finalproject.configurations.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import ru.maxima.finalproject.exceptions.UserNotFoundException;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.repositories.PersonRepository;

@Service
@RequiredArgsConstructor

public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        Person person = personRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return new PersonDetails(person);
    }
}
