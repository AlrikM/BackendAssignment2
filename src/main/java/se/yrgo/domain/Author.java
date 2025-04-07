package se.yrgo.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String nationality;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="AUTHOR_FK")
    private Set<Book> books;

    public Author() {};

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
        this.books = new HashSet<Book>();
    }
    public void addBook(Book book) {
        books.add(book);
    }

    public String getName(){
        return this.name;
    }
}
