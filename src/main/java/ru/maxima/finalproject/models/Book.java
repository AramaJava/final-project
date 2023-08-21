package ru.maxima.finalproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * @author AramaJava 09.08.2023
 */
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer yearOfProduction;
    private String  author;
    private String annotation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime removedAt;
    private String createdPerson;
    private String updatedPerson;
    private String removedPerson;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person owner;
}
