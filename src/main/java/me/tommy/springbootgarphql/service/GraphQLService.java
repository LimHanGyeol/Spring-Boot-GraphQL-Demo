package me.tommy.springbootgarphql.service;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import me.tommy.springbootgarphql.model.Book;
import me.tommy.springbootgarphql.repository.BookRepository;
import me.tommy.springbootgarphql.service.datafetcher.AllBooksDataFetcher;
import me.tommy.springbootgarphql.service.datafetcher.BookDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    private Resource resource;
    private GraphQL graphQL;

    private BookRepository bookRepository;
    private AllBooksDataFetcher allBooksDataFetcher;
    private BookDataFetcher bookDataFetcher;

    public GraphQLService(BookRepository bookRepository,
                          AllBooksDataFetcher allBooksDataFetcher,
                          BookDataFetcher bookDataFetcher) {
        this.bookRepository = bookRepository;
        this.allBooksDataFetcher = allBooksDataFetcher;
        this.bookDataFetcher = bookDataFetcher;
    }

    @PostConstruct
    public void loadSchema() throws IOException {
        loadDataIntoHSQL();
        File schemaFile = resource.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);

        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {
        Stream.of(
                new Book(123L, "Book of Clouds", "Kindle Edition",
                        "Chloe Ardiis", "Nov 2017"),
                new Book(124L, "Cloud Arch & Engineering", "Orielly",
                        "Peter", "Jan 2015"),
                new Book(125L, "Java 9 Programming", "Orielly",
                        "Ram", "Dec 2016")
        ).forEach(book -> {
            bookRepository.save(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                    typeWiring
                            .dataFetcher("allBooks", allBooksDataFetcher)
                            .dataFetcher("book", bookDataFetcher))
                            .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

}
