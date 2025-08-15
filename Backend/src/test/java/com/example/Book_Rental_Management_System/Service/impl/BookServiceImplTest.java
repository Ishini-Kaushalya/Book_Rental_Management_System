package com.example.Book_Rental_Management_System.Service.impl;

import com.example.Book_Rental_Management_System.Model.Book;
import com.example.Book_Rental_Management_System.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito for this test class
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository; // Mocked repository

    @InjectMocks
    private BookServiceImpl bookService;   // Service under test

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
    void addBook_ShouldReturnSavedBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book); // Mock save

        Book savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book); // Verify repository call
    }

    @Test
    void updateBook_WhenBookExists_ShouldReturnUpdatedBook() {
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setGenre("Non-Fiction");
        updatedBook.setAvailable(false);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book)); // Mock findById
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook); // Mock save

        Book result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        assertFalse(result.getAvailable());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void updateBook_WhenBookDoesNotExist_ShouldThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.updateBook(1L, book));
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(Book.class)); // Save should not be called
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() {
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(books); // Mock findAll

        List<Book> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_WhenBookDoesNotExist_ShouldThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.getBookById(1L));
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void deleteBook_ShouldCallRepositoryDelete() {
        doNothing().when(bookRepository).deleteById(1L); // Mock deleteById

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
