package ru.maxima.finalproject.services;

import ru.maxima.finalproject.models.Person;

/**
 * @author AramaJava 19.08.2023
 */

public interface JwtService {

    // создаем и возвращаем токен пользователя
    String getToken(Person person);

    // получ тек пользователя из токена
    Person getCurrentPersonFromToken();

}
