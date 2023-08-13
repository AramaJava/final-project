package ru.maxima.finalproject.configurations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author AramaJava 13.08.2023
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Authorities {

    @SuppressWarnings("InstantiationOfUtilityClass")
    @Getter
    private static final Authorities instance = new Authorities();

    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_USER = "User";

}
