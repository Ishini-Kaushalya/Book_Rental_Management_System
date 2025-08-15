package com.example.Book_Rental_Management_System.Service.impl;

import com.example.Book_Rental_Management_System.Model.Book;
import com.example.Book_Rental_Management_System.Model.Rental;
import com.example.Book_Rental_Management_System.Repository.BookRepository;
import com.example.Book_Rental_Management_System.Repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito annotations
class RentalServiceImplTest {

    @Mock
    private RentalRepository rentalRepository; // Mocked repository

    @Mock
    private BookRepository bookRepository; // Mocked repository for book availability

    @InjectMocks
    private RentalServiceImpl rentalService; // Service under test

    private Rental rental;
    private Book book;

    @BeforeEach
    void setUp() {
        // Create sample book and rental
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setAvailable(true);

        rental = new Rental();
        rental.setId(1L);
        rental.setUserName("Test User");
        rental.setUserEmail("test@example.com");
        rental.setUserPhone("1234567890");
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7));
        rental.setBook(book);
    }

    @Test
    void createRental_WhenBookIsAvailable_ShouldCreateRentalAndUpdateBookAvailability() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Rental result = rentalService.createRental(rental);

        assertNotNull(result);
        assertEquals("Test User", result.getUserName());
        assertFalse(book.getAvailable()); // Book marked as unavailable
        verify(bookRepository, times(1)).findById(1L);
        verify(rentalRepository, times(1)).save(rental);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void createRental_WhenBookIsNotAvailable_ShouldThrowException() {
        book.setAvailable(false);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertThrows(RuntimeException.class, () -> rentalService.createRental(rental));
        verify(bookRepository, times(1)).findById(1L);
        verify(rentalRepository, never()).save(any(Rental.class));
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void createRental_WhenBookDoesNotExist_ShouldThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> rentalService.createRental(rental));
        verify(bookRepository, times(1)).findById(1L);
        verify(rentalRepository, never()).save(any(Rental.class));
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void updateRental_WhenRentalExists_ShouldReturnUpdatedRental() {
        Rental updatedRental = new Rental();
        updatedRental.setUserName("Updated User");
        updatedRental.setUserEmail("updated@example.com");
        updatedRental.setUserPhone("9876543210");
        updatedRental.setRentalDate(LocalDate.now());
        updatedRental.setReturnDate(LocalDate.now().plusDays(14));
        updatedRental.setBook(book);

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(any(Rental.class))).thenReturn(updatedRental);

        Rental result = rentalService.updateRental(1L, updatedRental);

        assertNotNull(result);
        assertEquals("Updated User", result.getUserName());
        assertEquals("updated@example.com", result.getUserEmail());
        verify(rentalRepository, times(1)).findById(1L);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void updateRental_WhenRentalDoesNotExist_ShouldThrowException() {
        when(rentalRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> rentalService.updateRental(1L, rental));
        verify(rentalRepository, times(1)).findById(1L);
        verify(rentalRepository, never()).save(any(Rental.class));
    }

    @Test
    void getAllRentals_ShouldReturnAllRentals() {
        List<Rental> rentals = Arrays.asList(rental);
        when(rentalRepository.findAll()).thenReturn(rentals);

        List<Rental> result = rentalService.getAllRentals();

        assertEquals(1, result.size());
        verify(rentalRepository, times(1)).findAll();
    }

    @Test
    void getRentalById_WhenRentalExists_ShouldReturnRental() {
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        Rental result = rentalService.getRentalById(1L);

        assertNotNull(result);
        assertEquals("Test User", result.getUserName());
        verify(rentalRepository, times(1)).findById(1L);
    }

    @Test
    void getRentalById_WhenRentalDoesNotExist_ShouldThrowException() {
        when(rentalRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> rentalService.getRentalById(1L));
        verify(rentalRepository, times(1)).findById(1L);
    }
}
