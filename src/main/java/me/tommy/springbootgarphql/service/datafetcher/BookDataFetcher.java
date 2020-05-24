package me.tommy.springbootgarphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import me.tommy.springbootgarphql.model.Book;
import me.tommy.springbootgarphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDataFetcher implements DataFetcher<Optional<Book>> {

    BookRepository bookRepository;

    public BookDataFetcher(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long isn = dataFetchingEnvironment.getArgument("id");
        return bookRepository.findById(isn);
    }
}
