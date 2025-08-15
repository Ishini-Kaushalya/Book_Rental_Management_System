package com.example.Book_Rental_Management_System.Controller;

import com.example.Book_Rental_Management_System.Model.Rental;
import com.example.Book_Rental_Management_System.Service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Marks this as a REST controller
@RequestMapping("/api/rentals") // Base path for rental-related APIs
@CrossOrigin(origins = "*") // Allow requests from any domain (CORS)
public class RentalController {

    private final RentalService rentalService; // Service for rental logic

    // Constructor injection of RentalService
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping // Create a new rental record
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        Rental createdRental = rentalService.createRental(rental);
        return ResponseEntity.ok(createdRental);
    }

    @PutMapping("/{id}") // Update an existing rental by ID
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rental) {
        Rental updatedRental = rentalService.updateRental(id, rental);
        return ResponseEntity.ok(updatedRental);
    }

    @GetMapping // Get all rentals
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{id}") // Get a rental by its ID
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }
}
