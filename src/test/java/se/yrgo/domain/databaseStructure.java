package se.yrgo.domain;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;

import java.util.*;

public class databaseStructure {
    private EntityManagerFactory emf;
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Author author1 = new Author("Gustav","sv");
    Author author2 = new Author("Suarez","es");
    Author author3 = new Author("Brian","en");
    Book svBook1 = new Book("Upp And Down","Kids", 1989);
    Book svBook2 = new Book("Around Around We Go","Kids", 2002);
    author1.addBook(svBook1);
    author1.addBook(svBook2);
    em.persist(author1);
    Book esBook1 = new Book("Fair Bit Away","Romance", 1979);
    Book esBook2 = new Book("Turnabout Time","Drama", 2002);
    author2.addBook(esBook1);
    author2.addBook(esBook2);
    em.persist(author2);
    Book enBook1 = new Book("ABC", "Educational", 2015);
    author3.addBook(enBook1);
    em.persist(author3);
    Reader reader1 = new Reader("Farlo","oleni@gmail.com");
    reader1.addBook(svBook1);
    em.persist(reader1);
    Reader reader2 = new Reader("Heiskanen","Heine@gmail.com");
    reader2.addBook(svBook2);
    reader2.addBook(enBook1);
    em.persist(reader2);
    Reader reader3 = new Reader("David", "Pota@hotmail.com");
    reader3.addBook(enBook1);
    em.persist(reader3);
    tx.commit();
    em.close();
    }

    @Test
    public void task2(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q1 = em.createQuery("select author.books from Author as author where author.name = 'Gustav'");
        List<Book> books = q1.getResultList();
        for (Book book : books) {
            System.out.println(book.getTitle());
        };
        tx.commit();
        em.close();
    }

    @Test
    public void task3(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Book book = em.find(Book.class, 8);
        Query q1 = em.createQuery("from Reader reader where :book member of reader.books", Reader.class);
        q1.setParameter("book", book);
        List<Reader> readers = q1.getResultList();
        for (Reader reader : readers) {
            System.out.println(reader.getName());
        }
        tx.commit();
        em.close();
    }
    @Test
    public void task4(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q = em.createQuery("select distinct author from Author as author join author.books as book where book.reader is not empty");
        List<Author> authors = q.getResultList();
        for (Author author : authors) {
            System.out.println(author.getName());
        }
        tx.commit();
        em.close();
    }

    @Test
    public void task5(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query q = em.createQuery("select author.name, count(book) from Author as author join author.books as book group by author.name");
        List<Object[]> results = q.getResultList();
        for (Object[] row : results) {
            System.out.println(row[0] + " " + row[1]);
        }
        tx.commit();
        em.close();
    }

    @Test
    public void task6(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Book> books = em.createNamedQuery("searchByGenre", Book.class).setParameter("genre", "Kids").getResultList();
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
        tx.commit();
        em.close();
    }
}
