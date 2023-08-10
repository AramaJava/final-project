package ru.maxima.finalproject.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AramaJava 09.08.2023
 */

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime removedAt;
    private String createdPerson;
    private String removedPerson;
    @OneToMany(mappedBy = "owner")
    private List<Book> takenBooks;
}
