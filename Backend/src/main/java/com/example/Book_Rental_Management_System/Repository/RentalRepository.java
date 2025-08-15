package com.example.Book_Rental_Management_System.Repository;

import com.example.Book_Rental_Management_System.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByReturnDateBeforeAndBook_Available(LocalDate date, Boolean available);
}