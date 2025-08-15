package com.example.Book_Rental_Management_System.Service;

import com.example.Book_Rental_Management_System.Model.Rental;
import java.util.List;

public interface RentalService {
    Rental createRental(Rental rental);
    Rental updateRental(Long id, Rental rental);
    List<Rental> getAllRentals();
    Rental getRentalById(Long id);

    void updateBooksAvailability();
}
