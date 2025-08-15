package com.example.Book_Rental_Management_System.Service;

import com.example.Book_Rental_Management_System.Model.Book;
import java.util.List;

public interface BookService {
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<Book> getAllBooks();
    Book getBookById(Long id);
}
