package com.example.Book_Rental_Management_System.Controller;

import com.example.Book_Rental_Management_System.Model.Book;
import com.example.Book_Rental_Management_System.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito annotations
class BookControllerTest {

    @Mock
    private BookService bookService; // Mocked BookService for controller testing

    @InjectMocks
    private BookController bookController; // Controller under test

    private Book book;

    @BeforeEach
    void setUp() {
        // Create a sample book before each test
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setGenre("Fiction");
        book.setAvailable(true);
    }

    @Test
    void addBook_ShouldReturnCreatedBook() {
        when(bookService.addBook(any(Book.class))).thenReturn(book); // Mock service call

        ResponseEntity<Book> response = bookController.addBook(book); // Call controller method

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check HTTP status
        assertNotNull(response.getBody()); // Response body should not be null
        assertEquals("Test Book", response.getBody().getTitle()); // Check book title
        verify(bookService, times(1)).addBook(book); // Verify service was called once
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() {
        when(bookService.updateBook(anyLong(), any(Book.class))).thenReturn(book);

        ResponseEntity<Book> response = bookController.updateBook(1L, book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Book", response.getBody().getTitle());
        verify(bookService, times(1)).updateBook(1L, book);
    }

    @Test
    void deleteBook_ShouldCallServiceDelete() {
        doNothing().when(bookService).deleteBook(1L); // Mock delete action

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() {
        List<Book> books = Arrays.asList(book);
        when(bookService.getAllBooks()).thenReturn(books); // Mock service call

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size()); // Check list size
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById_ShouldReturnBook() {
        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Book", response.getBody().getTitle());
        verify(bookService, times(1)).getBookById(1L);
    }
}
