package ru.maxima.finalproject.services;

import ru.maxima.finalproject.models.Person;

public interface AuthService {

 //   void registration(Person user, Long adminId);

    String authentication(Person person);

}
