package se.yrgo.domain;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String genre;
    private int publicationYear;

    @ManyToMany(mappedBy = "books")
    private Set<Reader> reader;

    public Book() {};

    public Book(String title, String genre, int publicationYear) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.reader = new HashSet<Reader>();
    }

    public String getTitle() {
        return title;
    }
}
