package com.example.Book_Rental_Management_System.Controller;

import com.example.Book_Rental_Management_System.Model.Rental;
import com.example.Book_Rental_Management_System.Service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito annotations
class RentalControllerTest {

    @Mock
    private RentalService rentalService; // Mocked service layer

    @InjectMocks
    private RentalController rentalController; // Controller under test

    private Rental rental;

    @BeforeEach
    void setUp() {
        // Initialize a sample rental before each test
        rental = new Rental();
        rental.setId(1L);
        rental.setUserName("Test User");
        rental.setUserEmail("test@example.com");
        rental.setUserPhone("1234567890");
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7));
    }

    @Test
    void createRental_ShouldReturnCreatedRental() {
        when(rentalService.createRental(any(Rental.class))).thenReturn(rental); // Mock service

        ResponseEntity<Rental> response = rentalController.createRental(rental); // Call controller

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verify HTTP status
        assertNotNull(response.getBody()); // Response should not be null
        assertEquals("Test User", response.getBody().getUserName()); // Verify name
        verify(rentalService, times(1)).createRental(rental); // Verify service call
    }

    @Test
    void updateRental_ShouldReturnUpdatedRental() {
        when(rentalService.updateRental(anyLong(), any(Rental.class))).thenReturn(rental);

        ResponseEntity<Rental> response = rentalController.updateRental(1L, rental);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test User", response.getBody().getUserName());
        verify(rentalService, times(1)).updateRental(1L, rental);
    }

    @Test
    void getAllRentals_ShouldReturnAllRentals() {
        List<Rental> rentals = Arrays.asList(rental);
        when(rentalService.getAllRentals()).thenReturn(rentals);

        ResponseEntity<List<Rental>> response = rentalController.getAllRentals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size()); // Verify list size
        verify(rentalService, times(1)).getAllRentals();
    }

    @Test
    void getRentalById_ShouldReturnRental() {
        when(rentalService.getRentalById(1L)).thenReturn(rental);

        ResponseEntity<Rental> response = rentalController.getRentalById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test User", response.getBody().getUserName());
        verify(rentalService, times(1)).getRentalById(1L);
    }
}
