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

@ExtendWith(MockitoExtension.class)
class RentalControllerTest {

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalController rentalController;

    private Rental rental;

    @BeforeEach
    void setUp() {
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
        when(rentalService.createRental(any(Rental.class))).thenReturn(rental);

        ResponseEntity<Rental> response = rentalController.createRental(rental);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test User", response.getBody().getUserName());
        verify(rentalService, times(1)).createRental(rental);
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
        assertEquals(1, response.getBody().size());
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