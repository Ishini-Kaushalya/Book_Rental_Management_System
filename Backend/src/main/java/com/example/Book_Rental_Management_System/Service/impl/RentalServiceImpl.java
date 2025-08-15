package com.example.Book_Rental_Management_System.Service.impl;

import com.example.Book_Rental_Management_System.Model.Book;
import com.example.Book_Rental_Management_System.Model.Rental;
import com.example.Book_Rental_Management_System.Repository.BookRepository;
import com.example.Book_Rental_Management_System.Repository.RentalRepository;
import com.example.Book_Rental_Management_System.Service.RentalService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service // Marks this class as a Spring service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository; // Repository for rentals
    private final BookRepository bookRepository;     // Repository for books

    // Constructor injection
    public RentalServiceImpl(RentalRepository rentalRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Rental createRental(Rental rental) {
        // Fetch the book to be rented
        Book book = bookRepository.findById(rental.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check availability
        if (!book.getAvailable()) {
            throw new RuntimeException("Book is not available for rental");
        }

        // Mark book as unavailable and save
        book.setAvailable(false);
        bookRepository.save(book);

        // Save the rental record
        return rentalRepository.save(rental);
    }

    @Override
    public Rental updateRental(Long id, Rental rental) {
        // Find existing rental and update fields
        return rentalRepository.findById(id).map(existingRental -> {
            existingRental.setUserName(rental.getUserName());
            existingRental.setUserEmail(rental.getUserEmail());
            existingRental.setUserPhone(rental.getUserPhone());
            existingRental.setRentalDate(rental.getRentalDate());
            existingRental.setReturnDate(rental.getReturnDate());
            existingRental.setBook(rental.getBook());
            return rentalRepository.save(existingRental);
        }).orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    @Override
    public List<Rental> getAllRentals() {
        // Update book availability before returning all rentals
        updateBooksAvailability();
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(Long id) {
        // Get rental by ID or throw exception if not found
        return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    // Updates book availability for rentals past their return date
    public void updateBooksAvailability() {
        List<Rental> activeRentals = rentalRepository.findByReturnDateBeforeAndBook_Available(LocalDate.now(), false);
        for (Rental rental : activeRentals) {
            Book book = rental.getBook();
            if (book != null && !book.getAvailable() && rental.getReturnDate().isBefore(LocalDate.now())) {
                book.setAvailable(true);
                bookRepository.save(book);
            }
        }
    }
}
