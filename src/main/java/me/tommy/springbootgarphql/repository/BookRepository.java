package me.tommy.springbootgarphql.repository;

import me.tommy.springbootgarphql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
