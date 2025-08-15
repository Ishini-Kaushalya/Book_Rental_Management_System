package com.example.Book_Rental_Management_System.Service.impl;

import com.example.Book_Rental_Management_System.Model.Book;
import com.example.Book_Rental_Management_System.Repository.BookRepository;
import com.example.Book_Rental_Management_System.Service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this as a service component in Spring
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository; // Repository for database operations

    // Constructor injection of BookRepository
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book); // Save a new book
    }

    @Override
    public Book updateBook(Long id, Book book) {
        // Find existing book by ID, update fields, and save
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setGenre(book.getGenre());
            existingBook.setAvailable(book.getAvailable());
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book not found")); // Throw if book doesn't exist
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id); // Delete book by ID
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll(); // Get all books
    }

    @Override
    public Book getBookById(Long id) {
        // Get a book by ID or throw exception if not found
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
