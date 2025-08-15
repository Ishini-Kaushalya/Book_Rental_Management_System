package com.example.Book_Rental_Management_System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor
@Data               // Generates getters, setters, equals, hashCode, and toString
@Table(name ="book") // Maps this entity to the "book" table
@Entity              // Marks this class as a JPA entity
public class Book {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(name = "title", nullable = false) // Title column (required)
    private String title;

    @Column(name = "author", nullable = false) // Author column (required)
    private String author;

    @Column(name = "genre", nullable = false) // Genre column (required)
    private String genre;

    @Column(name = "available", nullable = false) // Availability column (required)
    private Boolean available = true; // Default is true (available for rent)

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public Boolean getAvailable() { return available; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailable(Boolean available) { this.available = available; }
}
