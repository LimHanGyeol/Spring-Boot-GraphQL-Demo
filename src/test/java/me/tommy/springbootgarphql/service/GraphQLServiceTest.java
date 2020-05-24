package me.tommy.springbootgarphql.service;

import me.tommy.springbootgarphql.model.Book;
import me.tommy.springbootgarphql.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

class GraphQLServiceTest {

    private List<Book> books;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        books = new ArrayList<>();
        Book book = new Book(123L, "Book of Clouds", "Kindle Edition",
                "Chloe Ardiis", "Nov 2017");

        books.add(book);
    }

    @Test
    public void getBooks() {
        given(bookRepository.findAll()).willReturn(books);
    }

    @Test
    public void getBook() {
        Book savedBook = books.get(0);

        assertThat(savedBook.getId(), is(123L));
        given(bookRepository.findById(123L)).willReturn(java.util.Optional.of(savedBook));
    }
}