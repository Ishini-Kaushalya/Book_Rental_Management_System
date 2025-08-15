package com.example.Book_Rental_Management_System.Repository;

import com.example.Book_Rental_Management_System.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring Data repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Inherits CRUD methods for Book entity (findAll, findById, save, delete, etc.)
    // Long is the type of the primary key
}
