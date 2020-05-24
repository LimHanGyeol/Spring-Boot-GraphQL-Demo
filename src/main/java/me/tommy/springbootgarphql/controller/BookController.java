package me.tommy.springbootgarphql.controller;

import graphql.ExecutionResult;
import me.tommy.springbootgarphql.service.GraphQLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    GraphQLService graphQLService;

    public BookController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping("/rest/books")
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
