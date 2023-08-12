package ru.maxima.finalproject.services;

import lombok.RequiredArgsConstructor;
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

    @Override
    public void registration(Person user, Long adminId) {

        Person personForSave = Person.builder()
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(LocalDateTime.now())
                .createdPerson(personRepository.findNameById(adminId))
                .build();

        personRepository.save(personForSave);
    }

    @Override
    public String authentication() {
        return null;
    }
}
