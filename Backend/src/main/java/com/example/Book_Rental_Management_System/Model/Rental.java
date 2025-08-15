package com.example.Book_Rental_Management_System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor
@Data               // Generates getters, setters, equals, hashCode, toString
@Table(name ="rental") // Maps this entity to the "rental" table
@Entity              // Marks this as a JPA entity
public class Rental {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(name = "userName", nullable = false) // Renter's name (required)
    private String userName;

    @Column(name = "userEmail", nullable = false) // Renter's email (required)
    private String userEmail;

    @Column(name = "userPhone", nullable = false) // Renter's phone number (required)
    private String userPhone;

    @Column(name = "rentalDate", nullable = false) // Date when book was rented
    private LocalDate rentalDate;

    @Column(name = "returnDate", nullable = false) // Date when book should be returned
    private LocalDate returnDate;

    // Relationship: Many rentals can reference the same book
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false) // Foreign key to book table
    private Book book;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public LocalDate getRentalDate() { return rentalDate; }
    public void setRentalDate(LocalDate rentalDate) { this.rentalDate = rentalDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}
