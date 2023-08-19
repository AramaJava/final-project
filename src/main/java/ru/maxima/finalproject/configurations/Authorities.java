package ru.maxima.finalproject.configurations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author AramaJava 13.08.2023
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Authorities {

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static final Authorities instance = new Authorities();

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";

}
