package com.example.Book_Rental_Management_System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name ="Book")
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "userName",nullable = false)
    private String userName;

    @Column(name = "userEmail",nullable = false)
    private String userEmail;

    @Column(name = "userPhone",nullable = false)
    private String userPhone;

    @Column(name = "rentalDate",nullable = false)
    private LocalDate rentalDate;

    @Column(name = "returnDate",nullable = false)
    private LocalDate returnDate;

    // Relationship to Book: Many rentals can be for the same book.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}

