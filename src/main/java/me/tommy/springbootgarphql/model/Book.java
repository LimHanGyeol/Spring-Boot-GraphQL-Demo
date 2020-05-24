package me.tommy.springbootgarphql.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Book {

    @Id
    private Long isn;
    private String title;
    private String publisher;
    private String authors;
    private String publishedDate;

    public Book() {
    }

    public Book(Long isn, String title, String publisher, String authors, String publishedDate) {
        this.isn = isn;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.publishedDate = publishedDate;
    }
}
