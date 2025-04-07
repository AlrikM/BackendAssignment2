package se.yrgo.domain;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;

    @ManyToMany
    private Set<Book> books;

    public Reader() {};

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
        this.books = new HashSet<Book>();
    }
    public String getName(){
        return this.name;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
