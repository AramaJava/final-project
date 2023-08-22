package ru.maxima.finalproject.exceptions;

/**
 * @author AramaJava 15.08.2023
 */

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("Book not found!");
    }
}
