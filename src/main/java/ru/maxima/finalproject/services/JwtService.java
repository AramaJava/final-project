package ru.maxima.finalproject.services;

import ru.maxima.finalproject.models.Person;

/**
 * @author AramaJava 19.08.2023
 */

public interface JwtService {

    // получ токен пользователя
    String getToken(Person person);

    // получ тек пользователя из токена
    Person getCurrentPersonFromToken();

}
