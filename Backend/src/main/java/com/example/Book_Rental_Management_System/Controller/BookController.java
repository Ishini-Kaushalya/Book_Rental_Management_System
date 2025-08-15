package com.example.Book_Rental_Management_System.Controller;

import com.example.Book_Rental_Management_System.Model.Book;
import com.example.Book_Rental_Management_System.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/books") // Base path for all book-related endpoints
@CrossOrigin(origins = "*") // Allows cross-origin requests (CORS)
public class BookController {

    private final BookService bookService; // Service layer dependency

    // Constructor injection for BookService
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping // Add a new book
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}") // Update an existing book by ID
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}") // Delete a book by ID
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping // Get all books
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}") // Get a specific book by ID
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
}
