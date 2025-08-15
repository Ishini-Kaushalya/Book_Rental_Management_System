package com.example.Book_Rental_Management_System.Repository;

import com.example.Book_Rental_Management_System.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
