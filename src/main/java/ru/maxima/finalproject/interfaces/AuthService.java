package ru.maxima.finalproject.interfaces;

import ru.maxima.finalproject.models.Person;

public interface AuthService {

    public void registration(Person user, Long adminId);

    public String authentication();
}
