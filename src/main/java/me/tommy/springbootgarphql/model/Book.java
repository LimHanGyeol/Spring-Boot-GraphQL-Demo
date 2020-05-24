package me.tommy.springbootgarphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    private Long id;
    private String title;
    private String publisher;
    private String authors;
    private String publishedDate;

    public Book() {
    }

    public Book(Long id, String title, String publisher, String authors, String publishedDate) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
}
