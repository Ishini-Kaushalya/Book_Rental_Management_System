package com.example.Book_Rental_Management_System.Repository;

import com.example.Book_Rental_Management_System.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    // Inherits CRUD methods for Rental entity (findAll, findById, save, delete, etc.)
    // Long is the type of the primary key

    // Custom query method:
    // Finds rentals where the return date is before the given date
    // and the related book has the specified availability status
    List<Rental> findByReturnDateBeforeAndBook_Available(LocalDate date, Boolean available);
}
