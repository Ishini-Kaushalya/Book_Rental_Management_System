package com.example.Book_Rental_Management_System.Service.impl;

import com.example.Book_Rental_Management_System.Model.Book;
import com.example.Book_Rental_Management_System.Model.Rental;
import com.example.Book_Rental_Management_System.Repository.BookRepository;
import com.example.Book_Rental_Management_System.Repository.RentalRepository;
import com.example.Book_Rental_Management_System.Service.RentalService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;

    public RentalServiceImpl(RentalRepository rentalRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Rental createRental(Rental rental) {
        Book book = bookRepository.findById(rental.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.getAvailable()) {
            throw new RuntimeException("Book is not available for rental");
        }
        book.setAvailable(false);
        bookRepository.save(book);
        return rentalRepository.save(rental);
    }

    @Override
    public Rental updateRental(Long id, Rental rental) {
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
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
    }
}

